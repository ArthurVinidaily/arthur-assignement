package com.arthurheliosassignment.api.fizzbuzz.models;

public class ResponseObject<T> {
    private int statusCode;
    private T data;

    // constructor
    public ResponseObject(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    // getters and setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
