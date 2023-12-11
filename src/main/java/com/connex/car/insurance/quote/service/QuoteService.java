package com.connex.car.insurance.quote.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.connex.car.insurance.quote.mapper.Mapper;

@Service
public class QuoteService {

    private Mapper ageMapper;
    private Mapper drivingExperienceMapper;
    private Mapper driverRecordMapper;
    private Mapper claimsMapper;

    @Autowired
    public QuoteService(@Qualifier("ageFactorMapper") Mapper ageMapper,
            @Qualifier("drivingExperienceMapper") Mapper drivingExperienceMapper,
            @Qualifier("driverRecordMapper") Mapper driverRecordMapper,
            @Qualifier("claimsMapper") Mapper claimsMapper) {
        this.ageMapper = ageMapper;
        this.drivingExperienceMapper = drivingExperienceMapper;
        this.driverRecordMapper = driverRecordMapper;
        this.claimsMapper = claimsMapper;
    }

    Optional<Double> getAgeFactor(Byte age) {
        Optional<Double> factor = ageMapper.getFactor(age);
        return factor;
    }

    Optional<Double> getDrivingExperienceFactor(Byte yearsOfExperience) {
        Optional<Double> factor = drivingExperienceMapper.getFactor(yearsOfExperience);
        return factor;
    }

    Optional<Double> getDriverRecordFactor(Byte accidents) {
        Optional<Double> factor = driverRecordMapper.getFactor(accidents);
        return factor;
    }

    Optional<Double> getClaimsFactor(Byte claims) {
        Optional<Double> factor = claimsMapper.getFactor(claims);
        return factor;
    }
}
