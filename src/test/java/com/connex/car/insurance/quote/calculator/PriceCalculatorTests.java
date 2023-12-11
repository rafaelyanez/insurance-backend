package com.connex.car.insurance.quote.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class PriceCalculatorTests {

    @Test
    public void testCalculateCarCurrentValue9YearsCost12000() {
        // prepare
        Double purchasePrice = 12000.0;
        Integer age = 9;
        Double expectedValue = 3916.45;
        Function<Integer, Double> calculateFinalValue = PriceCalculator.getFinalValueFunction(purchasePrice);
        // call
        Double currentValue = calculateFinalValue.apply(age);
        // assert
        assertEquals(expectedValue, currentValue, "Expected " + expectedValue + ", but got " + currentValue);
    }

    @Test
    public void testCalculateCarCurrentValue3YearsCost43000() {
        // prepare
        Double purchasePrice = 43000.0;
        Integer age = 3;
        Double expectedValue = 26407.37;
        Function<Integer, Double> calculateFinalValue = PriceCalculator.getFinalValueFunction(purchasePrice);
        // call
        Double currentValue = calculateFinalValue.apply(age);
        // assert
        assertEquals(expectedValue, currentValue, "Expected " + expectedValue + ", but got " + currentValue);
    }
}
