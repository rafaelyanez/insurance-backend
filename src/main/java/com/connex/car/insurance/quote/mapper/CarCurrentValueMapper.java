package com.connex.car.insurance.quote.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component("carCurrentValueMapper")
public class CarCurrentValueMapper implements Mapper {

    @Override
    public Optional<Double> getFactor(Byte currentValue) {
        // 0 represents <$30,000
        if (currentValue == 0) {
            return Optional.of(0.8);
        }
        // 1 represents >=$30,000 and <$60,000
        if (currentValue == 1) {
            return Optional.of(1.0);
        }
        // 2 represents >=$60,000 and <$100,000
        if (currentValue == 2) {
            return Optional.of(1.2);
        }
        // 3 represents >=$100,000 and <$150,000
        if (currentValue == 3) {
            return Optional.of(1.5);
        }
        // 4 represents >=$150,000 and <$200,000
        if (currentValue == 4) {
            return Optional.of(2.0);
        }
        return Optional.empty();
    }
}