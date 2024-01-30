package org.exceptions;

public class MyException extends Exception{
    private String message;

    public MyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
