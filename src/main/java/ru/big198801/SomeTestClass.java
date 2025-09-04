package ru.big198801;

import ru.big198801.annotation.AfterSuite;
import ru.big198801.annotation.BeforeSuite;
import ru.big198801.annotation.Test;

public class SomeTestClass {
    //without annotation
    public static void learnJava() {
        System.out.println("Java First!");
    }

    @BeforeSuite
    public static void wakeUp() {
        System.out.println("Wake Up!...");
    }
    @Test(priority = 2)
    public void dressUp() {
        System.out.println("Dressing...");
    }

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

    @AfterSuite
    public static void goSleep() {
        System.out.println("Sleeping...");
    }


}
