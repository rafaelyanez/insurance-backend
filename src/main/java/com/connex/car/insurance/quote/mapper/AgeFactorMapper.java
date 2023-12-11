package com.connex.car.insurance.quote.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component("ageFactorMapper")
public class AgeFactorMapper implements Mapper {

    @Override
    public Optional<Double> getFactor(Integer value) {
        if (value < 25) {
            return Optional.of(1.3);
        }
        if (value >= 25 && value < 40) {
            return Optional.of(1.0);
        }
        if (value >= 40 && value < 70) {
            return Optional.of(0.9);
        }
        return Optional.empty();
    }

}
