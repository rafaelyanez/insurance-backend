package com.connex.car.insurance.quote.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component("claimsMapper")
public class ClaimsMapper implements Mapper {

    @Override
    public Optional<Double> getFactor(Byte claims) {
        if (claims == 0) {
            return Optional.of(0.9);
        }
        if (claims == 1) {
            return Optional.of(1.2);
        }
        if (claims == 2 || claims == 3) {
            return Optional.of(1.5);
        }
        return Optional.empty();
    }

}
