package com.tk.parkingapi.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * An generic error message Json to be sent to the frontend
 * @param httpStatus Usually HttpStatus.CONFLICT or NOT_FOUND
 * @param message
 * @param timeStamp
 */

public record ErrorMessage (
        HttpStatus httpStatus,
        String message,
        LocalDateTime timeStamp
){}
