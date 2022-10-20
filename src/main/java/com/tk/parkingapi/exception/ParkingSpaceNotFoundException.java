package com.tk.parkingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParkingSpaceNotFoundException extends RuntimeException{
    public ParkingSpaceNotFoundException(int parkingSpaceId) {
        super("No parking space found by id: " + parkingSpaceId);
    }
}
