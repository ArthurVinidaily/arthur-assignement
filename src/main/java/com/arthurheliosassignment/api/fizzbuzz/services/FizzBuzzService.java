package com.arthurheliosassignment.api.fizzbuzz.services;

import java.util.List;

import com.arthurheliosassignment.api.fizzbuzz.models.FizzBuzzList;
import com.arthurheliosassignment.api.fizzbuzz.models.Statistics;

public interface FizzBuzzService {

    void resetRequestCounts();

    List<String> generateFizzBuzz(FizzBuzzList request);

    Statistics getStatistics();

    void validateIntegerParameter(Integer value, String paramName);
}