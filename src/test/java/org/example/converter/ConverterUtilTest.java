package org.example.converter;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class ConverterUtilTest {

    int[][] celsiusFahrenheitMapping = new int[][] {{10, 50}, {40, 104}, {0, 32}};

    @TestFactory
    Stream<DynamicTest> convertsToFahrenheitTest() {
        return Arrays.stream(celsiusFahrenheitMapping).map(entry -> {
            int celsius = entry[0];
            int fahrenheit = entry[1];
            return dynamicTest(
                    "C: " + entry[0] + " -> " + "F: " + entry[1],
                    () -> assertEquals(fahrenheit, ConverterUtil.convertCelsiusToFahrenheit(celsius))
            );
        });
    }

    @TestFactory
    Stream<DynamicTest> fahrenheitToCelsiusConvertsTest() {
        return Arrays.stream(celsiusFahrenheitMapping).map(entry -> {
            int celsius = entry[0];
            int fahrenheit = entry[1];
            return dynamicTest(
                    "F: " + entry[1] + " -> " + "C: " + entry[0],
                    () -> assertEquals(celsius, ConverterUtil.convertFahrenheitToCelsius(fahrenheit))
            );
        });
    }

}
