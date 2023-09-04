package com.arthurheliosassignment.api.fizzbuzz.exceptions;

public class NoStatisticsFoundException extends RuntimeException {

    public NoStatisticsFoundException() {
        super("No statistics found. No previous fizzbuzz request have been received");
    }
}
