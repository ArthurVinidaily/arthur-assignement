package com.arthurheliosassignment.api.fizzbuzz.models;

public class ErrorObject {
    private Integer statusCode;
    private String message;

    // constructor
    public ErrorObject(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    // getters and setters
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}