package com.example.webback.exception;

public class FailedRegisterUserException extends RuntimeException {
    public FailedRegisterUserException(String message) {
        super(message);
    }
}
