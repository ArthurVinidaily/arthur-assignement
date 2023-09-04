package com.arthurheliosassignment.api.fizzbuzz.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arthurheliosassignment.api.fizzbuzz.models.ErrorObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ErrorObject> error(HttpServletRequest request) {

        // Get the HTTP status code from the request
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // Get the error message (you can customize this based on your application's
        // needs)
        String errorMessage = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        // Create an ErrorObject to hold the status code and message
        ErrorObject errorObject = new ErrorObject(
                statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

}