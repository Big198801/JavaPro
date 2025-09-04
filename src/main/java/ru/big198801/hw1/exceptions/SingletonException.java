package ru.big198801.hw1.exceptions;

public class SingletonException extends RuntimeException{
    public static final String WARNING = "methods with count more than 1 no allowed";
    public SingletonException(){
        super(WARNING);
    }
}
