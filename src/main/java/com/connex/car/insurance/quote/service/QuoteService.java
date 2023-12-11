package com.connex.car.insurance.quote.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.connex.car.insurance.quote.calculator.PriceCalculator;
import com.connex.car.insurance.quote.dto.QuoteData;
import com.connex.car.insurance.quote.mapper.Mapper;

@Service
public class QuoteService {

    private Mapper ageMapper;
    private Mapper drivingExperienceMapper;
    private Mapper driverRecordMapper;
    private Mapper claimsMapper;
    private Mapper annualDrivingMapper;
    private Mapper insuranceHistoryMapper;
    private Mapper carCurrentValueMapper;

    @Autowired
    public QuoteService(@Qualifier("ageFactorMapper") Mapper ageMapper,
            @Qualifier("drivingExperienceMapper") Mapper drivingExperienceMapper,
            @Qualifier("driverRecordMapper") Mapper driverRecordMapper,
            @Qualifier("claimsMapper") Mapper claimsMapper,
            @Qualifier("annualDrivingMapper") Mapper annualDrivingMapper,
            @Qualifier("insuranceHistoryMapper") Mapper insuranceHistoryMapper,
            @Qualifier("carCurrentValueMapper") Mapper carCurrentValueMapper) {
        this.ageMapper = ageMapper;
        this.drivingExperienceMapper = drivingExperienceMapper;
        this.driverRecordMapper = driverRecordMapper;
        this.claimsMapper = claimsMapper;
        this.annualDrivingMapper = annualDrivingMapper;
        this.insuranceHistoryMapper = insuranceHistoryMapper;
        this.carCurrentValueMapper = carCurrentValueMapper;
    }

    Optional<Double> getAgeFactor(Integer age) {
        Optional<Double> factor = ageMapper.getFactor(age);
        return factor;
    }

    Optional<Double> getDrivingExperienceFactor(Integer yearsOfExperience) {
        Optional<Double> factor = drivingExperienceMapper.getFactor(yearsOfExperience);
        return factor;
    }

    Optional<Double> getDriverRecordFactor(Integer accidents) {
        Optional<Double> factor = driverRecordMapper.getFactor(accidents);
        return factor;
    }

    Optional<Double> getClaimsFactor(Integer claims) {
        Optional<Double> factor = claimsMapper.getFactor(claims);
        return factor;
    }

    Optional<Double> getAnnualDrivingFactor(Integer kilometres) {
        Optional<Double> factor = annualDrivingMapper.getFactor(kilometres);
        return factor;
    }

    Optional<Double> getInsuranceHistoryFactor(Integer insuranceHistory) {
        Optional<Double> factor = insuranceHistoryMapper.getFactor(insuranceHistory);
        return factor;
    }

    Optional<Double> getCarCurrentValueFactor(Integer currentValue) {
        Optional<Double> factor = carCurrentValueMapper.getFactor(currentValue);
        return factor;
    }

    public Optional<Double> getPremium(QuoteData quoteData, Double basePremium) {
        Integer age = this.calculateAgeOfCar(Integer.parseInt(quoteData.year()));
        Function<Integer, Double> calculateFinalValue = PriceCalculator.getFinalValueFunction(quoteData.carCost());
        Optional<Double> currentPriceFactor = this
                .getCarCurrentValueFactor(mapCarCostToIndex(calculateFinalValue.apply(age)));
        Optional<Double> ageFactor = this.getAgeFactor(quoteData.personAge());
        Optional<Double> annualDrivingFactor = this.getAnnualDrivingFactor(quoteData.kilometres());
        Optional<Double> claimsFactor = this.getClaimsFactor(quoteData.claims());
        Optional<Double> driverRecordFactor = this.getDriverRecordFactor(quoteData.accidents());
        Optional<Double> drivingExperienceFactor = this.getDrivingExperienceFactor(quoteData.drivingExperience());
        Optional<Double> insuranceHistoryFactor = this.getInsuranceHistoryFactor(quoteData.yearsInsuranceHistory());
        if (currentPriceFactor.isPresent()
                && ageFactor.isPresent()
                && annualDrivingFactor.isPresent()
                && claimsFactor.isPresent()
                && driverRecordFactor.isPresent()
                && drivingExperienceFactor.isPresent()
                && insuranceHistoryFactor.isPresent()
                && !quoteData.business()) {
            return Optional.of(basePremium *
                    currentPriceFactor.get() *
                    ageFactor.get() *
                    annualDrivingFactor.get() *
                    claimsFactor.get() *
                    driverRecordFactor.get() *
                    drivingExperienceFactor.get() *
                    insuranceHistoryFactor.get());

        }
        return Optional.empty();

    }

    private Integer calculateAgeOfCar(Integer year) {
        Integer currentYear = LocalDate.now().getYear();
        return currentYear - year == 0 ? 1 : currentYear - year;
    }

    private Integer mapCarCostToIndex(Double carCost) {
        if (carCost < 30000) {
            return 0;
        }
        if (carCost >= 30000 && carCost < 60000) {
            return 1;
        }
        if (carCost >= 60000 && carCost < 100000) {
            return 2;
        }
        if (carCost >= 100000 && carCost < 150000) {
            return 3;
        }
        if (carCost >= 150000 && carCost < 200000) {
            return 4;
        }
        return 5;
    }

}
