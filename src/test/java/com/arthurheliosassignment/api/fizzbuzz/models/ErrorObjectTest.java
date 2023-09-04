package com.arthurheliosassignment.api.fizzbuzz.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ErrorObjectTest {

    @Test
    public void testErrorObjectConstructorAndGetters() {
        ErrorObject errorObject = new ErrorObject(404, "Not Found");

        assertEquals(404, errorObject.getStatusCode());
        assertEquals("Not Found", errorObject.getMessage());
    }

    @Test
    public void testErrorObjectSetters() {
        ErrorObject errorObject = new ErrorObject(404, "Not Found");

        errorObject.setStatusCode(500);
        errorObject.setMessage("Internal Server Error");

        assertEquals(500, errorObject.getStatusCode());
        assertEquals("Internal Server Error", errorObject.getMessage());
    }

    @Test
    public void testErrorObjectNotNull() {
        ErrorObject errorObject = new ErrorObject(404, "Not Found");

        assertNotNull(errorObject);
    }
}
