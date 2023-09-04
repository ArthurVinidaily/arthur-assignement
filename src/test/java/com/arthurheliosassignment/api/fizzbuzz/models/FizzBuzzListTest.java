package com.arthurheliosassignment.api.fizzbuzz.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class FizzBuzzListTest {

    @Test
    public void testFizzBuzzListConstructorAndGetters() {
        FizzBuzzList fizzBuzzList = new FizzBuzzList(3, 5, 100, "Fizz", "Buzz");

        assertEquals(3, fizzBuzzList.getInt1());
        assertEquals(5, fizzBuzzList.getInt2());
        assertEquals(100, fizzBuzzList.getLimit());
        assertEquals("Fizz", fizzBuzzList.getStr1());
        assertEquals("Buzz", fizzBuzzList.getStr2());
    }

    @Test
    public void testFizzBuzzListSetters() {
        FizzBuzzList fizzBuzzList = new FizzBuzzList(3, 5, 100, "Fizz", "Buzz");

        fizzBuzzList.setInt1(2);
        fizzBuzzList.setInt2(4);
        fizzBuzzList.setLimit(50);
        fizzBuzzList.setStr1("Foo");
        fizzBuzzList.setStr2("Bar");

        assertEquals(2, fizzBuzzList.getInt1());
        assertEquals(4, fizzBuzzList.getInt2());
        assertEquals(50, fizzBuzzList.getLimit());
        assertEquals("Foo", fizzBuzzList.getStr1());
        assertEquals("Bar", fizzBuzzList.getStr2());
    }

    @Test
    public void testFizzBuzzListNotNull() {
        FizzBuzzList fizzBuzzList = new FizzBuzzList(3, 5, 100, "Fizz", "Buzz");

        assertNotNull(fizzBuzzList);
    }
}
