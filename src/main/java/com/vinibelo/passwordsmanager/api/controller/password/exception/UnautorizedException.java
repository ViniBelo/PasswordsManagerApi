package com.vinibelo.passwordsmanager.api.controller.password.exception;

public class UnautorizedException extends RuntimeException {
    public UnautorizedException(String message) {
        super(message);
    }
}
