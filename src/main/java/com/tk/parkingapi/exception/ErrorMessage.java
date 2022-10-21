package com.tk.parkingapi.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorMessage (
        HttpStatus httpStatus,
        String message,
        LocalDateTime timeStamp
){}
