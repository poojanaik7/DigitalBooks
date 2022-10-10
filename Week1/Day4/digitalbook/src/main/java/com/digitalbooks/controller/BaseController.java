package com.digitalbooks.controller;

import com.digitalbooks.exception.DigitalBookException;
import com.digitalbooks.exception.ResultNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

public class BaseController {

    @ExceptionHandler(SQLException.class)
    ResponseEntity<?> handleSqlException(SQLException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DigitalBookException.class)
    ResponseEntity<?> handleException(DigitalBookException ex) {
        return new ResponseEntity("Something went wrong. Please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResultNotFoundException.class)
    ResponseEntity<?> handleResultNotFoundException(ResultNotFoundException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
