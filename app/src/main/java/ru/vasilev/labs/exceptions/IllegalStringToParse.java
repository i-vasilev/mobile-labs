package ru.vasilev.labs.exceptions;

public class IllegalStringToParse extends Exception {
    public IllegalStringToParse(String message) {
        super(String.format("String %s for parsing is illegal", message));
    }

}
