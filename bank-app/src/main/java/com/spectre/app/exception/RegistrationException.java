package com.spectre.app.exception;

public class RegistrationException extends IllegalArgumentException {
    public RegistrationException(String message){
        super(message);
    }
}
