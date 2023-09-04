package com.arthurheliosassignment.api.fizzbuzz.controllers;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.arthurheliosassignment.api.fizzbuzz.models.FizzBuzzList;
import com.arthurheliosassignment.api.fizzbuzz.models.Statistics;
import com.arthurheliosassignment.api.fizzbuzz.services.FizzBuzzService;

public class FizzBuzzControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FizzBuzzService fizzBuzzService;

    private FizzBuzzController fizzBuzzController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fizzBuzzController = new FizzBuzzController(fizzBuzzService);
        mockMvc = MockMvcBuilders.standaloneSetup(fizzBuzzController).build();
    }

    @Test
    void testFizzBuzzEndpoint() throws Exception {
        // Arrange
        FizzBuzzList request = new FizzBuzzList(3, 5, 15, "Fizz", "Buzz");
        List<String> expectedResult = generateFizzBuzzResult();

        when(fizzBuzzService.generateFizzBuzz(request)).thenReturn(expectedResult);

        // Act and Assert
        mockMvc.perform(get("/api/v1/fizzbuzz")
                .param("int1", "3")
                .param("int2", "5")
                .param("limit", "15")
                .param("str1", "Fizz")
                .param("str2", "Buzz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(expectedResult.size())))
                .andExpect(jsonPath("$.data", contains(expectedResult.toArray())));
    }

    @Test
    void testStatisticsEndpoint() throws Exception {
        // Arrange
        Statistics statistics = createSampleStatistics();
        when(fizzBuzzService.getStatistics()).thenReturn(statistics);

        // Act and Assert
        mockMvc.perform(get("/api/v1/statistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.int1").value(statistics.getInt1()))
                .andExpect(jsonPath("$.data.int2").value(statistics.getInt2()))
                .andExpect(jsonPath("$.data.limit").value(statistics.getLimit()))
                .andExpect(jsonPath("$.data.str1").value(statistics.getStr1()))
                .andExpect(jsonPath("$.data.str2").value(statistics.getStr2()))
                .andExpect(jsonPath("$.data.count").value(statistics.getCount()));
    }

    private List<String> generateFizzBuzzResult() {
        return List.of("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14",
                "FizzBuzz");
    }

    private Statistics createSampleStatistics() {
        Statistics statistics = new Statistics();
        statistics.setInt1(3);
        statistics.setInt2(5);
        statistics.setLimit(15);
        statistics.setStr1("Fizz");
        statistics.setStr2("Buzz");
        statistics.setCount(3);
        return statistics;
    }
}
