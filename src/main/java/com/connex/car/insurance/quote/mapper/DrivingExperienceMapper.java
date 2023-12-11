package com.connex.car.insurance.quote.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component("drivingExperienceMapper")
public class DrivingExperienceMapper implements Mapper {

    @Override
    public Optional<Double> getFactor(Integer value) {
        if (value < 2) {
            return Optional.of(1.5);
        }
        if (value >= 2 && value < 5) {
            return Optional.of(1.3);
        }
        if (value >= 5 && value < 10) {
            return Optional.of(1.0);
        }
        return Optional.of(0.9);
    }

}
