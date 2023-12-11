package com.connex.car.insurance.quote.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component("driverRecordMapper")
public class DriverRecordMapper implements Mapper {

    @Override
    public Optional<Double> getFactor(Byte accidents) {
        if (accidents == 0) {
            return Optional.of(1.0);
        }
        if (accidents == 1) {
            return Optional.of(1.2);
        }
        return Optional.empty();
    }

}
