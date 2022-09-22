package com.digitalbooks.controller;

import com.digitalbooks.exception.DigitalBookException;
import com.digitalbooks.exception.ResultNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class BaseControllerTest {

    @InjectMocks
    BaseController baseController;

    @Test
    public void handleSqlExceptionTest(){
        baseController.handleSqlException(new SQLException("SQL Exception"));
    }

    @Test
    public void handleExceptionTest(){
        baseController.handleException(new DigitalBookException("Something went wrong please try again later"));
    }

    @Test
    public void handleResultNotFoundExceptionTest(){
        baseController.handleResultNotFoundException(new ResultNotFoundException("SQL Exception"));
    }
}
