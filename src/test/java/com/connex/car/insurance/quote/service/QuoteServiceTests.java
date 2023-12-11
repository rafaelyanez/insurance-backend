package com.connex.car.insurance.quote.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.connex.car.insurance.quote.mapper.AgeFactorMapper;
import com.connex.car.insurance.quote.mapper.AnnualDrivingMapper;
import com.connex.car.insurance.quote.mapper.CarCurrentValueMapper;
import com.connex.car.insurance.quote.mapper.ClaimsMapper;
import com.connex.car.insurance.quote.mapper.DriverRecordMapper;
import com.connex.car.insurance.quote.mapper.DrivingExperienceMapper;
import com.connex.car.insurance.quote.mapper.InsuranceHistoryMapper;

public class QuoteServiceTests {
    private static QuoteService service;

    @BeforeAll
    public static void setUp() {
        service = new QuoteService(new AgeFactorMapper(), new DrivingExperienceMapper(), new DriverRecordMapper(),
                new ClaimsMapper(), new AnnualDrivingMapper(), new InsuranceHistoryMapper(),
                new CarCurrentValueMapper());
    }

    @Test
    public void testGetAgeFactorForAgeUnder25() {
        // prepare
        Integer age = 22; // I'm using Integer as nobody has been over 122 years old :)
        Double expectedFactor = 1.3;
        // call
        Optional<Double> factor = service.getAgeFactor(age);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetAgeFactorForAgeBetween25And40() {
        // prepare
        Integer age = 39;
        Double expectedFactor = 1.0;
        // call
        Optional<Double> factor = service.getAgeFactor(age);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetAgeFactorForAgeBetween40And70() {
        // prepare
        Integer age = 40;
        Double expectedFactor = 0.9;
        // call
        Optional<Double> factor = service.getAgeFactor(age);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetAgeFactorForAbove70() {
        // prepare
        Integer age = 71;
        // call
        Optional<Double> factor = service.getAgeFactor(age);
        // assert
        assertFalse(factor.isPresent());
    }

    @Test
    public void testGetDrivingExperienceFactorLessThan2Years() {
        // prepare
        Integer yearsOfExperience = 1;
        Double expectedFactor = 1.5;
        // call
        Optional<Double> factor = service.getDrivingExperienceFactor(yearsOfExperience);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetDrivingExperienceFactorMoreOrEqualThan2YearsAndLessThan5() {
        // prepare
        Integer yearsOfExperience = 4;
        Double expectedFactor = 1.3;
        // call
        Optional<Double> factor = service.getDrivingExperienceFactor(yearsOfExperience);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetDrivingExperienceFactorMoreOrEqualThan5YearsAndLessThan10() {
        // prepare
        Integer yearsOfExperience = 5;
        Double expectedFactor = 1.0;
        // call
        Optional<Double> factor = service.getDrivingExperienceFactor(yearsOfExperience);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetDrivingExperienceFactorGreaterThanOrEqual10() {
        // prepare
        Integer yearsOfExperience = 10;
        Double expectedFactor = 0.9;
        // call
        Optional<Double> factor = service.getDrivingExperienceFactor(yearsOfExperience);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetDriverRecordFactor0Accidents() {
        // prepare
        Integer accidents = 0;
        Double expectedFactor = 1.0;
        // call
        Optional<Double> factor = service.getDriverRecordFactor(accidents);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetDriverRecordFactor1Accident() {
        // prepare
        Integer accidents = 1;
        Double expectedFactor = 1.1;
        // call
        Optional<Double> factor = service.getDriverRecordFactor(accidents);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetDriverRecordFactor3Accident() {
        // prepare
        Integer accidents = 3;
        Double expectedFactor = 1.3;
        // call
        Optional<Double> factor = service.getDriverRecordFactor(accidents);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetDriverRecordFactorMoreThan3Accident() {
        // prepare
        Integer accidents = 4;
        // call
        Optional<Double> factor = service.getDriverRecordFactor(accidents);
        // assert
        assertFalse(factor.isPresent());
    }

    @Test
    public void testGetClaimsFactor0Claims() {
        // prepare
        Integer claims = 0;
        Double expectedFactor = 0.9;
        // call
        Optional<Double> factor = service.getClaimsFactor(claims);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetClaimsFactor1Claim() {
        // prepare
        Integer claims = 1;
        Double expectedFactor = 1.2;
        // call
        Optional<Double> factor = service.getClaimsFactor(claims);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetClaimsFactor2Claims() {
        // prepare
        Integer claims = 2;
        Double expectedFactor = 1.5;
        // call
        Optional<Double> factor = service.getClaimsFactor(claims);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetClaimsFactor4Claims() {
        // prepare
        Integer claims = 4;
        // call
        Optional<Double> factor = service.getClaimsFactor(claims);
        // assert
        assertFalse(factor.isPresent());
    }

    @Test
    public void testGetAnnualDrivingFactorLessThan20000km() {
        // prepare
        Integer kilometres = 0; // 0 represents the range of values under 20000km
        Double expectedFactor = 0.9;
        // call
        Optional<Double> factor = service.getAnnualDrivingFactor(kilometres);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetAnnualDrivingFactorBetween20000kmAnd30000km() {
        // prepare
        Integer kilometres = 1; // 1 represents the range >=20,000km<30,000km
        Double expectedFactor = 1.0;
        // call
        Optional<Double> factor = service.getAnnualDrivingFactor(kilometres);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetAnnualDrivingFactorBetween30000kmAnd50000km() {
        // prepare
        Integer kilometres = 2; // 2 represents the range >=30,000km<50,000km
        Double expectedFactor = 1.1;
        // call
        Optional<Double> factor = service.getAnnualDrivingFactor(kilometres);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetAnnualDrivingFactorOver50000km() {
        // prepare
        Integer kilometres = 3; // 2 represents the range >=50,000km
        Double expectedFactor = 1.3;
        // call
        Optional<Double> factor = service.getAnnualDrivingFactor(kilometres);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetAnnualDrivingFactorInvalidValue() {
        // prepare
        Integer kilometres = 4; // this value does not represent any range
        // call
        Optional<Double> factor = service.getAnnualDrivingFactor(kilometres);
        // assert
        assertFalse(factor.isPresent());
    }

    @Test
    public void testGetInsuranceHistoryFactorNoInsurance() {
        // prepare
        Integer insuranceHistory = 0;
        Double expectedFactor = 1.2;
        // call
        Optional<Double> factor = service.getInsuranceHistoryFactor(insuranceHistory);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetInsuranceHistoryFactorLessThanOrEqual2() {
        // prepare
        Integer insuranceHistory = 2;
        Double expectedFactor = 1.1;
        // call
        Optional<Double> factor = service.getInsuranceHistoryFactor(insuranceHistory);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetInsuranceHistoryFactorMoreThan2() {
        // prepare
        Integer insuranceHistory = 3;
        Double expectedFactor = 1.0;
        // call
        Optional<Double> factor = service.getInsuranceHistoryFactor(insuranceHistory);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetCarCurrentValueFactorCurrentValueLessThan30000() {
        // prepare
        Integer currentValue = 0; // 0 represents <$30,000
        Double expectedFactor = 0.8;
        // call
        Optional<Double> factor = service.getCarCurrentValueFactor(currentValue);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetCarCurrentValueFactorCurrentValueBetween30000And60000() {
        // prepare
        Integer currentValue = 1; // 1 represents >=$30,000 and <$60,000
        Double expectedFactor = 1.0;
        // call
        Optional<Double> factor = service.getCarCurrentValueFactor(currentValue);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetCarCurrentValueFactorCurrentValueBetween60000And100000() {
        // prepare
        Integer currentValue = 2; // 2 represents >=$60,000 and <$100,000
        Double expectedFactor = 1.2;
        // call
        Optional<Double> factor = service.getCarCurrentValueFactor(currentValue);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetCarCurrentValueFactorCurrentValueBetween100000And150000() {
        // prepare
        Integer currentValue = 3; // 3 represents >=$100,000 and <$150,000
        Double expectedFactor = 1.5;
        // call
        Optional<Double> factor = service.getCarCurrentValueFactor(currentValue);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetCarCurrentValueFactorCurrentValueBetween150000And200000() {
        // prepare
        Integer currentValue = 4; // 4 represents >=$150,000 and <$200,000
        Double expectedFactor = 2.0;
        // call
        Optional<Double> factor = service.getCarCurrentValueFactor(currentValue);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }

    @Test
    public void testGetCarCurrentValueFactorCurrentValueOver200000() {
        // prepare
        Integer currentValue = 5; // 5 represents >=$200,000
        // call
        Optional<Double> factor = service.getCarCurrentValueFactor(currentValue);
        // assert
        assertFalse(factor.isPresent());
    }

}
