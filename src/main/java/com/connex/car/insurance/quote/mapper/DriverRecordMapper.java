package com.connex.car.insurance.quote.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component("driverRecordMapper")
public class DriverRecordMapper implements Mapper {

    @Override
    public Optional<Double> getFactor(Integer accidents) {
        if (accidents == 0) {
            return Optional.of(1.0);
        }
        if (accidents == 1) {
            return Optional.of(1.1);
        }
        if (accidents == 2 || accidents == 3) {
            return Optional.of(1.3);
        }
        return Optional.empty();
    }

}
