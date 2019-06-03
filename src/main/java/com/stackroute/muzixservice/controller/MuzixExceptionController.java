package com.stackroute.muzixservice.controller;

import com.stackroute.muzixservice.exception.MuzixAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
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
        return new ResponseEntity("Muzix already exist",HttpStatus.ALREADY_REPORTED);
    }
}
