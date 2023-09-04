package com.arthurheliosassignment.packingsolution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ApproximatePackingOptimizationTest {

    @Test
    public void testNextFit() {
        List<String> result = ApproximatePackingOptimization.nextFit("135798642");
        assertEquals(List.of("135", "7", "9", "8", "64", "2"), result);
    }

    @Test
    public void testFirstFit() {
        List<String> result = ApproximatePackingOptimization.firstFit("35915798642");
        assertEquals(List.of("351", "9", "54", "72", "9", "8", "6"), result);
    }

    @Test
    public void testBestFit() {
        List<String> result = ApproximatePackingOptimization.bestFit("35915798642");
        assertEquals(List.of("352", "91", "5", "7", "9", "8", "64"), result);
    }

    @Test
    public void testWorstFit() {
        List<String> result = ApproximatePackingOptimization.worstFit("35915798642");
        assertEquals(List.of("351", "9", "54", "7", "9", "8", "62"), result);
    }
}