package com.connex.car.insurance.quote.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.connex.car.insurance.quote.dto.request.Quote;
import com.connex.car.insurance.quote.dto.response.QuoteData;
import com.connex.car.insurance.quote.service.QuoteService;

@RestController
public class QuoteController {
    private static final Logger log = LogManager.getLogger(QuoteController.class);

    @Autowired
    private QuoteService service;

    @PostMapping("/quote")
    @CrossOrigin(origins = "https://frontend-dot-insurance-407608.uk.r.appspot.com")
    public Quote quote(@RequestBody QuoteData quoteData) {
        log.info("Quote Data from the client ==> " + quoteData);
        Optional<Double> factor = Optional.empty();
        return new Quote(factor.get(), "RD1314234", true);
    }

    @GetMapping("/quote")
    @CrossOrigin(origins = "https://frontend-dot-insurance-407608.uk.r.appspot.com")
    public Quote testQuote() {
        return this.quote(new QuoteData("2013", 12000.0, false, 2, 3, 4, 29, 10));
    }
}
