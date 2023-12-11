package com.connex.car.insurance.quote.calculator;

import java.util.function.Function;

public class PriceCalculator {

    private static final double RATE1 = 0.15; // 15% for the first 3 years
    private static final double RATE2 = 0.10; // 10% thereafter

    public static Function<Integer, Double> getFinalValueFunction(Double initialCost) {
        return age -> {
            if (age <= 3) {
                return Math.floor(initialCost * Math.pow(1 - RATE1, age) * 100) / 100;
            } else {
                return Math.floor(initialCost * Math.pow(1 - RATE1, 3) * Math.pow(1 - RATE2, age - 3) * 100) / 100;
            }
        };
    }
}
