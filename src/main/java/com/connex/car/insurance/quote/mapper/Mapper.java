package com.connex.car.insurance.quote.mapper;

import java.util.Optional;

public interface Mapper {
    public Optional<Double> getFactor(Byte value);
}