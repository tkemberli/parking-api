package com.tk.parkingapi.exception;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler {

    public ResponseEntity<ErrorMessage> handleException(GenericNotFoundException exception){
        val status = HttpStatus.NOT_FOUND;
        val errorMessage = new ErrorMessage(status, exception.getMessage(), LocalDateTime.now());

        return new ResponseEntity<ErrorMessage>(errorMessage, status);
    }

    public ResponseEntity<ErrorMessage> handleException(GenericConflictException exception){
        val status = HttpStatus.CONFLICT;
        val errorMessage = new ErrorMessage(status, exception.getMessage(), LocalDateTime.now());

        return new ResponseEntity<ErrorMessage>(errorMessage, status);
    }
}