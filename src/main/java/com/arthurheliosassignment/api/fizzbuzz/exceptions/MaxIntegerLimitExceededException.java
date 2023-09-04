package com.arthurheliosassignment.api.fizzbuzz.exceptions;

public class MaxIntegerLimitExceededException extends RuntimeException {

    public MaxIntegerLimitExceededException(String params, Integer MAX_LIMIT) {
        super("Required request parameter '" + params + "' should be lower than " + MAX_LIMIT);
    }
}
