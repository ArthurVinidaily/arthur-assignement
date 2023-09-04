package com.arthurheliosassignment.api.fizzbuzz.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxIntegerLimitExceededExceptionTest {

    private static String MAX_EXCEEDED_MESSAGE = "Required request parameter 'param' should be lower than 100";

    @Test
    public void testMaxIntegerLimitExceededExceptionTestMessage() {
        MaxIntegerLimitExceededException exception = new MaxIntegerLimitExceededException("param", 100);

        String expectedMessage = MAX_EXCEEDED_MESSAGE;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
