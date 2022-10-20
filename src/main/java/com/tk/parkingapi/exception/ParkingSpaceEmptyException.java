package com.tk.parkingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParkingSpaceEmptyException extends RuntimeException{
    public ParkingSpaceEmptyException(int parkingSpaceID) {
        super("Parking space is empty by ID: " + parkingSpaceID);
    }
}
