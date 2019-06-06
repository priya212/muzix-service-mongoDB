package com.stackroute.muzixservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MuzixExceptionController {
    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<Object> exception(TrackNotFoundException exception)
    {
        return  new ResponseEntity("Track not found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = MuzixAlreadyExistsException.class)
    public ResponseEntity<Object> exception (MuzixAlreadyExistsException exception)
    {
        return new ResponseEntity("Muzix already exist",HttpStatus.CONFLICT/*ALREADY_REPORTED*/);
    }
}
