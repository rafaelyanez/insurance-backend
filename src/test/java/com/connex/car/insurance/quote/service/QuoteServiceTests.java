package com.connex.car.insurance.quote.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.connex.car.insurance.quote.mapper.AgeFactorMapper;
import com.connex.car.insurance.quote.mapper.AnnualDrivingMapper;
import com.connex.car.insurance.quote.mapper.ClaimsMapper;
import com.connex.car.insurance.quote.mapper.DriverRecordMapper;
import com.connex.car.insurance.quote.mapper.DrivingExperienceMapper;
import com.connex.car.insurance.quote.mapper.InsuranceHistoryMapper;

public class QuoteServiceTests {
    private static QuoteService service;

    @BeforeAll
    public static void setUp() {
        service = new QuoteService(new AgeFactorMapper(), new DrivingExperienceMapper(), new DriverRecordMapper(),
                new ClaimsMapper(), new AnnualDrivingMapper(), new InsuranceHistoryMapper());
    }

    @Test
    public void testGetAgeFactorForAgeUnder25() {
        // prepare
        Byte age = 22; // I'm using byte as nobody has been over 122 years old :)
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
        Byte age = 39;
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
        Byte age = 40;
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
        Byte age = 71;
        // call
        Optional<Double> factor = service.getAgeFactor(age);
        // assert
        assertFalse(factor.isPresent());
    }

    @Test
    public void testGetDrivingExperienceFactorLessThan2Years() {
        // prepare
        Byte yearsOfExperience = 1;
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
        Byte yearsOfExperience = 4;
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
        Byte yearsOfExperience = 5;
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
        Byte yearsOfExperience = 10;
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
        Byte accidents = 0;
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
        Byte accidents = 1;
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
        Byte accidents = 3;
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
        Byte accidents = 4;
        // call
        Optional<Double> factor = service.getDriverRecordFactor(accidents);
        // assert
        assertFalse(factor.isPresent());
    }

    @Test
    public void testGetClaimsFactor0Claims() {
        // prepare
        Byte claims = 0;
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
        Byte claims = 1;
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
        Byte claims = 2;
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
        Byte claims = 4;
        // call
        Optional<Double> factor = service.getClaimsFactor(claims);
        // assert
        assertFalse(factor.isPresent());
    }

    @Test
    public void testGetAnnualDrivingFactorLessThan20000km() {
        // prepare
        Byte kilometres = 0; // 0 represents the range of values under 20000km
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
        Byte kilometres = 1; // 1 represents the range >=20,000km<30,000km
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
        Byte kilometres = 2; // 2 represents the range >=30,000km<50,000km
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
        Byte kilometres = 3; // 2 represents the range >=50,000km
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
        Byte kilometres = 4; // this value does not represent any range
        // call
        Optional<Double> factor = service.getAnnualDrivingFactor(kilometres);
        // assert
        assertFalse(factor.isPresent());
    }

    @Test
    public void testGetInsuranceHistoryFactorNoInsurance() {
        // prepare
        Byte insuranceHistory = 0;
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
        Byte insuranceHistory = 2;
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
        Byte insuranceHistory = 3;
        Double expectedFactor = 1.0;
        // call
        Optional<Double> factor = service.getInsuranceHistoryFactor(insuranceHistory);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }
}
