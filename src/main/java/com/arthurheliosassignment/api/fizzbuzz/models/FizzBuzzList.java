package com.arthurheliosassignment.api.fizzbuzz.models;

import java.util.Objects;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class FizzBuzzList {
    private Integer int1;
    private Integer int2;
    private Integer limit;
    private String str1;
    private String str2;

    // constructor
    public FizzBuzzList(Integer int1, Integer int2, Integer limit, String str1, String str2) {
        this.int1 = int1;
        this.int2 = int2;
        this.limit = limit;
        this.str1 = str1;
        this.str2 = str2;
    }

    // getters and setters
    public Integer getInt1() {
        return int1;
    }

    public void setInt1(Integer int1) {
        this.int1 = int1;
    }

    public Integer getInt2() {
        return int2;
    }

    public void setInt2(Integer int2) {
        this.int2 = int2;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FizzBuzzList that = (FizzBuzzList) o;
        return int1 == that.int1 &&
                int2 == that.int2 &&
                limit == that.limit &&
                Objects.equals(str1, that.str1) &&
                Objects.equals(str2, that.str2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(int1, int2, limit, str1, str2);
    }
}