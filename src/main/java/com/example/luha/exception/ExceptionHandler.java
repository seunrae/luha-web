package com.example.luha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException exception){
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
   }
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistException.class)
   public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
