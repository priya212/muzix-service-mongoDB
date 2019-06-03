package com.stackroute.muzixservice.exception;

public class MuzixAlreadyExistsException extends Exception {
    private String message;

    public MuzixAlreadyExistsException() {
    }

    public MuzixAlreadyExistsException(String message) {
        this.message = message;
    }
}
