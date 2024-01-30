package org.exceptions;

public class EmptyCollectionError extends Exception{
    private final String message;

    public EmptyCollectionError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
