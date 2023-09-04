package com.arthurheliosassignment.api.fizzbuzz.services.impl;

import org.springframework.stereotype.Service;

import com.arthurheliosassignment.api.fizzbuzz.exceptions.MaxIntegerLimitExceededException;
import com.arthurheliosassignment.api.fizzbuzz.exceptions.NoStatisticsFoundException;
import com.arthurheliosassignment.api.fizzbuzz.models.FizzBuzzList;
import com.arthurheliosassignment.api.fizzbuzz.models.Statistics;
import com.arthurheliosassignment.api.fizzbuzz.services.FizzBuzzService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FizzBuzzServiceImpl implements FizzBuzzService {

    private static Integer MAX_LIMIT = 10000;

    private final Map<FizzBuzzList, Integer> requestCounts = new HashMap<>();

    public Map<FizzBuzzList, Integer> getRequestCounts() {
        return this.requestCounts;
    }

    @Override
    public void resetRequestCounts() {
        requestCounts.clear();
    }

    @Override
    public List<String> generateFizzBuzz(FizzBuzzList request) {
        Integer int1 = request.getInt1();
        Integer int2 = request.getInt2();
        Integer limit = request.getLimit();
        String str1 = request.getStr1();
        String str2 = request.getStr2();

        List<String> result = new ArrayList<>();

        for (int i = 1; i <= limit; i++) {
            if (i % (int1 * int2) == 0) {
                result.add(str1 + str2);
            } else if (i % int1 == 0) {
                result.add(str1);
            } else if (i % int2 == 0) {
                result.add(str2);
            } else {
                result.add(String.valueOf(i));
            }
        }
        // Update the request count
        requestCounts.put(request, requestCounts.getOrDefault(request, 0) + 1);

        return result;
    }

    @Override
    public Statistics getStatistics() {
        // Find the most frequent request
        FizzBuzzList mostFrequentRequest = null;
        int maxCount = 0;

        if (requestCounts.size() == 0) {
            throw new NoStatisticsFoundException();
        }

        for (Map.Entry<FizzBuzzList, Integer> entry : requestCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentRequest = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        // Create the statistics response
        Statistics statistics = new Statistics();
        if (mostFrequentRequest != null) {
            statistics.setInt1(mostFrequentRequest.getInt1());
            statistics.setInt2(mostFrequentRequest.getInt2());
            statistics.setLimit(mostFrequentRequest.getLimit());
            statistics.setStr1(mostFrequentRequest.getStr1());
            statistics.setStr2(mostFrequentRequest.getStr2());
            statistics.setCount(maxCount);
        }

        return statistics;
    }

    public void validateIntegerParameter(Integer value, String paramName) {
        if (value > MAX_LIMIT) {
            throw new MaxIntegerLimitExceededException(paramName, MAX_LIMIT);
        }
    }
}
