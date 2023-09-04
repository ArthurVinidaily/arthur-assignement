package com.arthurheliosassignment.api.fizzbuzz.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jakarta.servlet.RequestDispatcher;

@WebMvcTest(CustomErrorController.class)
public class CustomErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestDispatcher requestDispatcher;

    // Constants for request attributes
    private static final String ERROR_STATUS_CODE = RequestDispatcher.ERROR_STATUS_CODE;
    private static final String ERROR_MESSAGE = RequestDispatcher.ERROR_MESSAGE;

    @Test
    void error() throws Exception {
        // Set up the request attributes
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute(ERROR_STATUS_CODE, HttpStatus.NOT_FOUND.value());
        request.setAttribute(ERROR_MESSAGE, "Not Found");

        mockMvc.perform(MockMvcRequestBuilders.get("/error")
                .requestAttr(ERROR_STATUS_CODE, HttpStatus.NOT_FOUND.value())
                .requestAttr(ERROR_MESSAGE, "Not Found"))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Not Found"));
    }
}
