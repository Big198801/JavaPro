package ru.big198801;

public class Employee {
    private String name;
    private int age;
    private Position position;

    public Employee(int age, String name, Position position) {
        this.age = age;
        this.name = name;
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
}
