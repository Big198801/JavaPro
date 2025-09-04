package ru.big198801.hw1;

import ru.big198801.hw1.annotation.AfterSuite;
import ru.big198801.hw1.annotation.BeforeSuite;
import ru.big198801.hw1.annotation.Test;


public class SomeTestClass {
    //without annotation
    public static void learnJava() {
        System.out.println("Java First!");
    }

    @Test(priority = 2)
    public void dressUp() {
        System.out.println("Dressing...");
    }

    @BeforeSuite
    public static void wakeUp() {
        System.out.println("Wake Up!...i'm STATIC");
    }

    @AfterSuite
    public static void goSleep() {
        System.out.println("Sleeping... i'm STATIC");
    }
    //    @AfterSuite //second AfterSuite not allowed
    //    public static void goSleep2() {
    //        System.out.println("Sleeping2222... i'm STATIC");
    //    }

    @Test(priority = 1)
    public void washUp() {
        System.out.println("Washing...");
    }

    @Test(priority = 3)
    public void eatUp() {
        System.out.println("Eating food...");
    }

    @Test(priority = 4)
    public void working() {
        System.out.println("Working in progress...");
    }

    @Test() //will be default 5
    public void chillUp() {
        System.out.println("Chilling...");
    }
}
