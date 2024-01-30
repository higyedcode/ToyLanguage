package org.exceptions;

public class DeclarationError extends Exception{
    private final String message;

    public DeclarationError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
