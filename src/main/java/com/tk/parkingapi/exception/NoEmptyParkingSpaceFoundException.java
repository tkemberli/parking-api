package com.tk.parkingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoEmptyParkingSpaceFoundException extends RuntimeException{
    public NoEmptyParkingSpaceFoundException() {
        super("All parking spaces are occupied");
    }
}
