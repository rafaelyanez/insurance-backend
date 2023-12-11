package com.connex.car.insurance.quote.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.connex.car.insurance.quote.mapper.AgeFactorMapper;
import com.connex.car.insurance.quote.mapper.DriverRecordMapper;
import com.connex.car.insurance.quote.mapper.DrivingExperienceMapper;

public class QuoteServiceTests {
    private static QuoteService service;

    @BeforeAll
    public static void setUp() {
        service = new QuoteService(new AgeFactorMapper(), new DrivingExperienceMapper(), new DriverRecordMapper());
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
        // call
        Optional<Double> factor = service.getDrivingExperienceFactor(yearsOfExperience);
        // assert
        assertFalse(factor.isPresent());
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
        Double expectedFactor = 1.2;
        // call
        Optional<Double> factor = service.getDriverRecordFactor(accidents);
        // assert
        assertTrue(factor.isPresent());
        assertEquals(expectedFactor, factor.get());
    }
}
