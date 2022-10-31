package com.tk.parkingapi.exception;

/**
 * Exception for trowing conflicts like; trying to park a car into an nonexistent space, trying to GET an non parked car
 */

public class GenericNotFoundException extends RuntimeException{
    public GenericNotFoundException(String message) {
        super(message);
    }
}
