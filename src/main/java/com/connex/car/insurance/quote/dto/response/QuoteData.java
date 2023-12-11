package com.connex.car.insurance.quote.dto.response;

public record QuoteData(String year, Double carCost, Boolean business, Integer kilometres, Integer accidents,
                Integer claims, Integer personAge, Integer yearsInsuranceHistory) {

}
