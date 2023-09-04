package com.arthurheliosassignment.api.fizzbuzz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arthurheliosassignment.api.fizzbuzz.models.FizzBuzzList;
import com.arthurheliosassignment.api.fizzbuzz.models.ResponseObject;
import com.arthurheliosassignment.api.fizzbuzz.models.Statistics;
import com.arthurheliosassignment.api.fizzbuzz.services.FizzBuzzService;

@RestController
@RequestMapping("/api/v1")
public class FizzBuzzController {

    // Create an instance of FizzBuzzService
    private final FizzBuzzService fizzBuzzService;

    @Autowired
    public FizzBuzzController(FizzBuzzService fizzBuzzService) {
        this.fizzBuzzService = fizzBuzzService;
    }

    @GetMapping("/fizzbuzz")
    public ResponseEntity<ResponseObject<List<String>>> fizzBuzz(
            @RequestParam(name = "int1") Integer int1,
            @RequestParam(name = "int2") Integer int2,
            @RequestParam(name = "limit") Integer limit,
            @RequestParam(name = "str1") String str1,
            @RequestParam(name = "str2") String str2) {

        fizzBuzzService.validateIntegerParameter(int1, "int1");
        fizzBuzzService.validateIntegerParameter(int2, "int1");
        fizzBuzzService.validateIntegerParameter(limit, "limit");

        // Create an instance of FizzBuzzRequest
        FizzBuzzList request = new FizzBuzzList(int1, int2, limit, str1, str2);

        List<String> result = fizzBuzzService.generateFizzBuzz(request);
        ResponseObject<List<String>> response = new ResponseObject<List<String>>(HttpStatus.OK.value(), result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Define the statistics endpoint
    @GetMapping("/statistics")
    public ResponseEntity<ResponseObject<Statistics>> getStatistics() {
        Statistics statistics = fizzBuzzService.getStatistics();
        ResponseObject<Statistics> response = new ResponseObject<Statistics>(HttpStatus.OK.value(), statistics);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}