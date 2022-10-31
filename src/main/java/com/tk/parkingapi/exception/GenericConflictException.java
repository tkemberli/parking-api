package com.tk.parkingapi.exception;

/**
 * Exception for trowing conflicts like; trying to park a car into an occupied space
 */

public class GenericConflictException extends RuntimeException{
    public GenericConflictException(String message) {
        super(message);
    }
}
