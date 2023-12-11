package com.connex.car.insurance.quote.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component("insuranceHistoryMapper")
public class InsuranceHistoryMapper implements Mapper {

    @Override
    public Optional<Double> getFactor(Integer insuranceHistory) {
        if (insuranceHistory == 0) {
            return Optional.of(1.2);
        }
        if (insuranceHistory == 1 || insuranceHistory == 2) {
            return Optional.of(1.1);
        }
        if (insuranceHistory > 2) {
            return Optional.of(1.0);
        }
        return Optional.empty();
    }

}
