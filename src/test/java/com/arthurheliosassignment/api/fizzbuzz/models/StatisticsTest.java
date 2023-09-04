package com.arthurheliosassignment.api.fizzbuzz.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class StatisticsTest {

    @Test
    public void testStatisticsGettersAndSetters() {
        Statistics statistics = new Statistics();
        statistics.setInt1(3);
        statistics.setInt2(5);
        statistics.setLimit(100);
        statistics.setStr1("Fizz");
        statistics.setStr2("Buzz");
        statistics.setCount(42);

        assertEquals(3, statistics.getInt1());
        assertEquals(5, statistics.getInt2());
        assertEquals(100, statistics.getLimit());
        assertEquals("Fizz", statistics.getStr1());
        assertEquals("Buzz", statistics.getStr2());
        assertEquals(42, statistics.getCount());
    }

    @Test
    public void testStatisticsNotNull() {
        Statistics statistics = new Statistics();

        assertNotNull(statistics);
    }
}
