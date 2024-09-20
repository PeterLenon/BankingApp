package com.example.Exceptions;

public class EmailAddressTakenError extends Exception {
    public EmailAddressTakenError(String message) {
        super(message);
    }
}