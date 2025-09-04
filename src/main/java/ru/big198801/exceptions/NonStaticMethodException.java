package ru.big198801.exceptions;

public class NonStaticMethodException extends RuntimeException {
    public static final String WARNING = "non-static methods not allowed here";

    public NonStaticMethodException() {
        super(WARNING);
    }
}
