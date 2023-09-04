# Helios Technical Test: Fizz Buzz & XspeedIt

This project encompasses two technical tasks: Fizz Buzz and XspeedIt. Below, we provide a detailed overview of each task along with instructions on how to use them.

## Task 1: Fizz Buzz

### Description

The original Fizz Buzz problem involves replacing multiples of a given integer with a certain string multiples of another with another string and multiples of both with "fizzbuzz" while counting from 1 to a certain limit. In this task, we implement a web server exposing a REST API endpoint that performs this operation with customizable parameters.

### Fizzbuzz Endpoint

- **Endpoint**: `api/v1/fizzbuzz`
- **HTTP Method**: GET

#### Request Parameters

- `int1` (integer): The first integer.
- `int2` (integer): The second integer.
- `limit` (integer): The limit of numbers to process.
- `str1` (string): The string to replace multiples of `int1`.
- `str2` (string): The string to replace multiples of `int2`.

#### Sample Request

```http
GET /fizzbuzz?int1=3&int2=5&limit=100&str1=fizz&str2=buzz
```

#### Sample Response
```json
{
    "statusCode": 200,
    "data": [
        "1",
        "2",
        "fizz",
        "4",
        "buzz",
        "fizz",
        "7",
        "8",
        "fizz",
        "buzz",
        ...
        ]
}
```

### Statistics Endpoint

- **Endpoint**: `api/v1/statistics`
- **HTTP Method**: GET

#### Sample Response

```json
{
    "statusCode" : 200,
    "data": {
      "int1": 3,
      "int2": 5,
      "limit": 100,
      "str1": "fizz",
      "str2": "buzz",
      "count": 3
    }
}
```
#### Testings

We are doing unit testing and endpoints testing all over our api through 32 tests. To run the tests: 
```
mvn test
```

#### Error handling

We are hanldling all the errors in a model call ErrorObject with the appropriate status code. For example, if we try to pass a string in the "int1" parameter : 
```http
GET /api/v1/fizzbuzz?int1=notaninteger&int2=5&limit=100&str1=fizz&str2=buzz
```
We got :

```json
{
    "statusCode" : 400,
    "message": "Required request parameter 'int1' must be of type 'java.lang.Integer'"
}
```

#### Monitoring

To monitor the health of our API, we are using Prometheus. To access all the metrics: 
```http
GET /actuator/prometheus
```
To see all handle paths:
```http
GET /actuator
```

Feel free to add the metrics you may need.

#### Documentation

We are using swagger to document our API. Feel free to customize the dashboard as you wish. 
Here is the link to access it when the app is running:
```http
GET /swagger-ui/index.html
```

## Task 2: XspeedIt

### Description

XspeedIt is an import/export company that has automated its packaging process. The goal is to optimize the number of cartons used for packaging articles of variable sizes. Each carton has a capacity of 10, and articles are represented by integers from 1 to 9. We need to implement an algorithm to maximize the number of articles per carton.

### Algorithm

The algorithm takes a string of items sizes (e.g., "163841689525773") and optimally packs them into cartons, minimizing carton usage. It is a bin packing problems. We have tested 4 classics "heuristiques" algorithms : Next fit (which is the classic one), First FIT, Best Fit and Worst Fit. "Exact" algorithms like linear regressions have been implemented because not very appropriate for our use case. Here are some optimization factors and complexity for these algorithms: 
| Algorithm       | Approximation Guarantee | Worst Case Complexity      |
|-----------------|-------------------------|---------------------------|
| Next-fit (NF)   | ≤ 2*OPT - 1             | O(L)                    |
| First-fit (FF)  | ≤ ⌊ 1.7*OPT ⌋         | O((L * log(L))         |
| Best-fit (BF)   | ≤ ⌊ 1.7*OPT ⌋         | O((L * log(L))         |
| Worst-fit (WF)  | ≤ 2*OPT - 1             |  O((L * log(L))         |

[Source: Wikipedia](https://en.wikipedia.org/wiki/Bin_packing_problem)

### Getting Started

To use the XspeedIt functionality, follow these steps:

1. Clone this repository.
2. Build and run the Java application including the inputItems arguments:
```
mvn spring-boot:run -DinputItemsToPacked=456789765456769
```
3. The different algorithms results are logged after the build logs:
```
Input Items: 456789765456769

Next Fit: 45/6/7/8/9/7/6/54/5/6/7/6/9
Total Cartons Used: 13

First Fit: 45/64/7/8/9/7/6/55/6/7/6/9
Total Cartons Used: 12

Best Fit: 45/64/7/8/9/7/6/55/6/7/6/9
Total Cartons Used: 12

Worst Fit: 45/6/7/8/9/7/6/54/5/6/7/6/9
Total Cartons Used: 13
```

## Development Information

- Language: Java
- software project management and comprehension tool: Maeven
- Framework: Spring Boot
- Libraries: Swagger and Prometheus are integrated for monitoring and documentation.
- Code is structured to be production-ready and easily maintainable.


## Dependencies

Here are the dependencies used in this project:

- **Spring Boot**: The framework for building the web server.
  - Version: 3.1.3 (as specified in the parent POM)

- **Spring Boot Test**: Used for testing.

- **Spring Boot Web**: Used for web development.

- **Spring Boot DevTools**: Developer tools for Spring Boot (Optional).

- **JUnit Jupiter**: Testing framework.

- **Spring Boot Actuator**: Provides production-ready features.
  - Version: 2.1.0.RELEASE

- **Micrometer Registry Prometheus**: Prometheus monitoring for Micrometer.
  - Version: 1.11.3

- **SpringDoc OpenAPI**: Used for API documentation.
  - Version: 2.0.2

These are the key dependencies used in the project. Be sure to update the versions as needed.



