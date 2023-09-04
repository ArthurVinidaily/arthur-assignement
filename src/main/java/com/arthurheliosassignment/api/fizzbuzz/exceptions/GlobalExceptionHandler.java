package com.arthurheliosassignment.api.fizzbuzz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.arthurheliosassignment.api.fizzbuzz.models.ErrorObject;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorObject> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        String errorMessage = "The requested endpoint is not found.";
        ErrorObject errorObject = new ErrorObject(HttpStatus.NOT_FOUND.value(), errorMessage);

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorObject> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        String type = ex.getParameterType();

        ErrorObject errorObject = new ErrorObject(HttpStatus.BAD_REQUEST.value(),
                "Required request parameter '" + name + "' of type '" + type + "' is missing");

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {

        String name = ex.getName();
        Class<?> requiredType = ex.getRequiredType();

        ErrorObject errorObject = new ErrorObject(HttpStatus.BAD_REQUEST.value(),
                "Required request parameter '" + name + "' must be of type '" + requiredType.getName() + "'");

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoStatisticsFoundException.class)
    public ResponseEntity<ErrorObject> handleNoStatisticsFound(NoStatisticsFoundException ex) {

        String errorMessage = ex.getMessage();
        ErrorObject errorObject = new ErrorObject(HttpStatus.NOT_FOUND.value(), errorMessage);

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaxIntegerLimitExceededException.class)
    public ResponseEntity<ErrorObject> handleMaxIntegerLimitExceededException(MaxIntegerLimitExceededException ex) {

        String errorMessage = ex.getMessage();
        ErrorObject errorObject = new ErrorObject(HttpStatus.BAD_REQUEST.value(), errorMessage);

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }
}