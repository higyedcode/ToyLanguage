package org.exceptions;

public class ImproperTypeError extends Exception{
    private final String message;

    public ImproperTypeError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
