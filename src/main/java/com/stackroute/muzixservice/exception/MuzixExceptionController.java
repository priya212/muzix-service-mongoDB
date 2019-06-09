package com.stackroute.muzixservice.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MuzixExceptionController {
    @Value("${spring.muzix.muzixException}")
    private String muzixException;

    @Value("S{spring.muzix.alreadyExistException}")
    private String muzixAlreadyExist;
    @Value("${spring.muzix.exception}")
    private  String getAllException;

    //Handles global exception for muzixNotFound
    @ExceptionHandler(value = MuzixNotFoundException.class)
    public ResponseEntity<Object> exception(MuzixNotFoundException exception)
    {
        return  new ResponseEntity(muzixException, HttpStatus.NOT_FOUND);
    }
    //Handles global exception for muzixAlreadyExist
    @ExceptionHandler(value = MuzixAlreadyExistsException.class)
    public ResponseEntity<Object> exception (MuzixAlreadyExistsException exception)
    {
        return new ResponseEntity(muzixAlreadyExist,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception (Exception exception)
    {
        return new ResponseEntity( getAllException,HttpStatus.CONFLICT);
    }
}
