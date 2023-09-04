package com.arthurheliosassignment.api.fizzbuzz.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.arthurheliosassignment.api.fizzbuzz.models.ErrorObject;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    private static String NO_STATISTICS_MESSAGE = "No statistics found. No previous fizzbuzz request have been received";
    private static String NO_HANDLER_MESSAGE = "The requested endpoint is not found.";

    @BeforeEach
    public void setUp() {
        // Initialize any Mockito annotated mocks
        MockitoAnnotations.openMocks(this);
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testHandleNoHandlerFoundException() {
        NoHandlerFoundException ex = new NoHandlerFoundException("GET", "/api/resource", null);

        ResponseEntity<ErrorObject> response = globalExceptionHandler.handleNoHandlerFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(NO_HANDLER_MESSAGE, response.getBody().getMessage());
    }

    @Test
    public void testHandleMissingParams() {
        MissingServletRequestParameterException ex = new MissingServletRequestParameterException("paramName",
                "paramType");

        ResponseEntity<ErrorObject> response = globalExceptionHandler.handleMissingParams(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Required request parameter 'paramName' of type 'paramType' is missing",
                response.getBody().getMessage());
    }

    @Test
    public void testHandleNoStatisticsFound() {
        NoStatisticsFoundException ex = new NoStatisticsFoundException();

        ResponseEntity<ErrorObject> response = globalExceptionHandler.handleNoStatisticsFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(NO_STATISTICS_MESSAGE, response.getBody().getMessage());
    }

    @Test
    public void testMaxIntegerLimitExceededException() {
        MaxIntegerLimitExceededException ex = new MaxIntegerLimitExceededException("param", 1000);

        ResponseEntity<ErrorObject> response = globalExceptionHandler.handleMaxIntegerLimitExceededException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Required request parameter 'param' should be lower than 1000", response.getBody().getMessage());
    }

    @Test
    public void testMethodArgumentTypeMismatchException() throws Exception {
        MethodArgumentTypeMismatchException ex = new MethodArgumentTypeMismatchException("test", getClass(), "param",
                null, null);

        ResponseEntity<ErrorObject> response = globalExceptionHandler.handleMethodArgumentTypeMismatchException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(
                "Required request parameter 'param' must be of type 'com.arthurheliosassignment.api.fizzbuzz.exceptions.GlobalExceptionHandlerTest'",
                response.getBody().getMessage());
    }

}
