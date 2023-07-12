package com.calculatorTests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CalculatorApiTest {

   // private final String calculatorIp = System.getProperty("calculator.ip");
    // private final int calculatorPort = Integer.parseInt(System.getProperty("calculator.port"));

    @Test
    public void testFirstExpression() throws InterruptedException {
        System.out.println("Running testFirstExpression - (3*7+4)*0.25");
        // Arrange
        String expression = "(3*7+4)*0.25";
        String expectedValue = "{\"result\":6.25}";

        // Act
        String response = evaluateExpression(expression);
        System.out.println("Response was: " + response);

        // Assert
        assert response.equals(expectedValue) : "The calculated value doesn't match the expected value.";
        Thread.sleep(5000);
    }

    @Test
    public void testSecondExpression() throws InterruptedException {
        System.out.println("Running testSecondExpression - (6*7+3)*6");
        // Arrange
        String expression = "(6*7+3)*6";
        String expectedValue = "{\"result\":270.0}";

        // Act
        String response = evaluateExpression(expression);
        System.out.println("Response was: " + response);

        // Assert
        assert response.equals(expectedValue) : "The calculated value doesn't match the expected value.";
        Thread.sleep(5000);
    }

    @Test
    public void testSimple() throws InterruptedException {
        System.out.println("Running testSimple - 42");
        // Arrange
        String expression = "42";
        String expectedValue = "{\"result\":42.0}";

        // Act
        String response = evaluateExpression(expression);
        System.out.println("Response was: " + response);

        // Assert
        assert response.equals(expectedValue) : "The calculated value doesn't match the expected value.";
        Thread.sleep(5000);
    }

    @Test
    public void testError() throws InterruptedException {
        System.out.println("Running testError - 6:4");
        // Arrange
        String expression = "6:4";
        String expectedValue = "{\"result\":\"Invalid math exception: ' 6:4 '\"}";

        // Act
        String response = evaluateExpression(expression);
        System.out.println("Response was: " + response);

        // Assert
        assert response.equals(expectedValue) : "The calculated value doesn't match the expected value.";
        Thread.sleep(5000);
    }

    @Test
    public void testLong() throws InterruptedException {
        System.out.println("Running testLong - (6+(4*3)*1.5-6)+4*3");
        // Arrange
        String expression = "(6+(4*3)*1.5-6)+4*3";
        String expectedValue = "{\"result\":30.0}";

        // Act
        String response = evaluateExpression(expression);
        System.out.println("Response was: " + response);

        // Assert
        assert response.equals(expectedValue) : "The calculated value doesn't match the expected value.";
        Thread.sleep(5000);
    }

    private String evaluateExpression(String expression) {
        String url = "http://" + "localhost" + ":" + 9080 + "/api/evaluate/" + expression;

        // Make the API call and get the response
        Response response = RestAssured.get(url);

        // Extract the response as a string
        String responseBody = response.getBody().asString();

        return responseBody;
    }
    private String evaluateExpression2(String expression) {
        String url = "http://" + "localhost" + ":" + 9080 + "/evaluate/" + expression;

        // Make the API call and get the response
        Response response = RestAssured.get(url);

        // Extract the response as a string
        String responseBody = response.getBody().asString();

        return responseBody;
    }
}
