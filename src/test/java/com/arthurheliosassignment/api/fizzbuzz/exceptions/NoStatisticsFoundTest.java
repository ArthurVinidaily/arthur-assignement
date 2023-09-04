
package com.arthurheliosassignment.api.fizzbuzz.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NoStatisticsFoundTest {

    private static String NO_STATISTICS_MESSAGE = "No statistics found. No previous fizzbuzz request have been received";

    @Test
    public void testNoStatisticsFoundMessage() {
        NoStatisticsFoundException exception = new NoStatisticsFoundException();

        String expectedMessage = NO_STATISTICS_MESSAGE;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
