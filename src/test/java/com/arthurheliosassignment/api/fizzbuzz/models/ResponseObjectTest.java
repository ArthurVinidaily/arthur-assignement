package com.arthurheliosassignment.api.fizzbuzz.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ResponseObjectTest {

    @Test
    public void testResponseObjectConstructorAndGetters() {
        ResponseObject<String> responseObject = new ResponseObject<>(200, "Success");

        assertEquals(200, responseObject.getStatusCode());
        assertEquals("Success", responseObject.getData());
    }

    @Test
    public void testResponseObjectSetters() {
        ResponseObject<String> responseObject = new ResponseObject<>(200, "Success");

        responseObject.setStatusCode(404);
        responseObject.setData("Not Found");

        assertEquals(404, responseObject.getStatusCode());
        assertEquals("Not Found", responseObject.getData());
    }

    @Test
    public void testResponseObjectNotNull() {
        ResponseObject<String> responseObject = new ResponseObject<>(200, "Success");

        assertNotNull(responseObject);
    }
}
