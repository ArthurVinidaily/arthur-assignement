package com.arthurheliosassignment.api.fizzbuzz.services;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.arthurheliosassignment.api.fizzbuzz.exceptions.NoStatisticsFoundException;
import com.arthurheliosassignment.api.fizzbuzz.models.FizzBuzzList;
import com.arthurheliosassignment.api.fizzbuzz.models.Statistics;
import com.arthurheliosassignment.api.fizzbuzz.services.impl.FizzBuzzServiceImpl;

public class FizzBuzzServiceImplTest {

    private FizzBuzzService fizzBuzzService;

    @BeforeEach
    void setUp() {
        fizzBuzzService = new FizzBuzzServiceImpl();
    }

    @Test
    void generateFizzBuzzSizeTest() {
        FizzBuzzList request = createSampleFizzBuzzRequest();
        List<String> result = fizzBuzzService.generateFizzBuzz(request);
        assertEquals(15, result.size());
    }

    @Test
    void generateFizzBuzzNotNullTest() {
        FizzBuzzList request = createSampleFizzBuzzRequest();
        List<String> result = fizzBuzzService.generateFizzBuzz(request);
        assertNotNull(result);
    }

    @Test
    void generateFizzBuzzRequestCountsUpdateTest() {
        FizzBuzzList request = createSampleFizzBuzzRequest();
        FizzBuzzServiceImpl fizzBuzzServiceImpl = (FizzBuzzServiceImpl) fizzBuzzService;

        Map<FizzBuzzList, Integer> requestCounts = fizzBuzzServiceImpl.getRequestCounts();
        requestCounts.put(request, 0);

        fizzBuzzService.generateFizzBuzz(request);

        assertEquals(1, requestCounts.get(request));
    }

    @Test
    void generateFizzBuzzResultTest() {
        FizzBuzzList request = createSampleFizzBuzzRequest();
        List<String> result = fizzBuzzService.generateFizzBuzz(request);

        String[] expected = {
                "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"
        };

        assertArrayEquals(expected, result.toArray(new String[0]));
    }

    @Test
    void getStatisticsTest() {
        FizzBuzzList request1 = createSampleFizzBuzzRequest();
        FizzBuzzList request2 = createSampleFizzBuzzRequest();
        request2.setInt1(2); // Change int1 for request2

        FizzBuzzServiceImpl fizzBuzzServiceImpl = (FizzBuzzServiceImpl) fizzBuzzService;
        Map<FizzBuzzList, Integer> requestCounts = fizzBuzzServiceImpl.getRequestCounts();

        requestCounts.put(request1, 3);
        requestCounts.put(request2, 2);

        Statistics statistics = fizzBuzzService.getStatistics();

        assertNotNull(statistics);
        assertEquals(3, statistics.getInt1());
        assertEquals(5, statistics.getInt2());
        assertEquals(15, statistics.getLimit());
        assertEquals("Fizz", statistics.getStr1());
        assertEquals("Buzz", statistics.getStr2());
        assertEquals(3, statistics.getCount());
    }

    @Test
    void getStatisticsNoStatisticsFoundTest() {
        assertThrows(NoStatisticsFoundException.class, () -> fizzBuzzService.getStatistics());
    }

    // Helper method to create a sample FizzBuzzList request
    private FizzBuzzList createSampleFizzBuzzRequest() {
        return new FizzBuzzList(3, 5, 15, "Fizz", "Buzz");
    }
}
