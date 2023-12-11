package com.connex.car.insurance.quote.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.connex.car.insurance.quote.dto.Quote;
import com.connex.car.insurance.quote.dto.QuoteData;
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
        Optional<Double> premium = service.getPremium(quoteData, 2000.0);
        if (premium.isPresent()) {
            return new Quote(premium.get(), "Q" + quoteData.hashCode(), true);
        } else {
            return new Quote(null, null, false);
        }
    }

}
