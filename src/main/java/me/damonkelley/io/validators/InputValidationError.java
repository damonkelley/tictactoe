package me.damonkelley.io.validators;

public class InputValidationError extends RuntimeException {
    public InputValidationError(String message) {
        super(message);
    }
}
