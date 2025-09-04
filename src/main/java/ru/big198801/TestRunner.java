package ru.big198801;

import ru.big198801.annotation.AfterSuite;
import ru.big198801.annotation.BeforeSuite;
import ru.big198801.annotation.Test;
import ru.big198801.exceptions.NonStaticMethodException;
import ru.big198801.exceptions.SingletonException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestRunner {
    private static List<Method> test = new ArrayList<>();
    private static List<Method> beforeSuit = new ArrayList<>();
    private static List<Method> afterSuit = new ArrayList<>();
    private static List<Method> finalListOfMethods = new ArrayList<>();

    public static void runTests(Class<SomeTestClass> testRunnerClazz) throws Exception {
        Class<?> someTestClassClass = Class.forName(testRunnerClazz.getName());
        Method[] methods = someTestClassClass.getDeclaredMethods();
        sortMethodsByAnnotationName(methods);
        checkSuiteMethods(beforeSuit);
        checkSuiteMethods(afterSuit);
        uniteAllMethods();
        Constructor<SomeTestClass> testRunnerConstructor = testRunnerClazz.getConstructor();
        SomeTestClass testRunner =  testRunnerConstructor.newInstance();
        invokeMethods(testRunner);

    }

    private static void sortMethodsByAnnotationName(Method[] methods) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                test.add(method);
            } else if (method.isAnnotationPresent(BeforeSuite.class)) {
                beforeSuit.add(method);
            } else if  (method.isAnnotationPresent(AfterSuite.class)) {
                afterSuit.add(method);
            }
        }
    }

    public static void checkSuiteMethods(List<Method> methodList){
        if (methodList.size() > 1) throw new SingletonException();
        for (Method method : methodList){
            if (!Modifier.isStatic(method.getModifiers())) throw new NonStaticMethodException();
        }
    }

    private static void uniteAllMethods() {
        finalListOfMethods.addAll(beforeSuit);
        test.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));
        finalListOfMethods.addAll(test);
        finalListOfMethods.addAll(afterSuit);
    }

    private static void invokeMethods(SomeTestClass someTestClassClass) {
        for (Method method : finalListOfMethods){
            try {
                method.setAccessible(true);
                method.invoke(someTestClassClass);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
