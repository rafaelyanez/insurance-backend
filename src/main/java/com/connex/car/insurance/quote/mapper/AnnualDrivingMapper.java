package com.connex.car.insurance.quote.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component("annualDrivingMapper")
public class AnnualDrivingMapper implements Mapper {

    @Override
    public Optional<Double> getFactor(Byte kilometres) {
        // 0 represents < 20000km
        if (kilometres == 0) {
            return Optional.of(0.9);
        }
        // 1 represents >=20,000km<30,000km
        if (kilometres == 1) {
            return Optional.of(1.0);
        }
        // 2 represents >=30,000km<50,000km
        if (kilometres == 2) {
            return Optional.of(1.1);
        }
        // 3 represents >=50,000km
        if (kilometres == 3) {
            return Optional.of(1.3);
        }
        return Optional.empty();
    }

}
