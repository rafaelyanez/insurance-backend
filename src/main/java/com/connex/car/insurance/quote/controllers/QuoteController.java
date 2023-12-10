package com.connex.car.insurance.quote.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.connex.car.insurance.quote.dto.request.Quote;
import com.connex.car.insurance.quote.dto.response.QuoteData;

@RestController
public class QuoteController {
    private static final Logger log = LogManager.getLogger(QuoteController.class);

    @PostMapping("/quote")
    @CrossOrigin(origins = "http://localhost:5173")
    public Quote quote(@RequestBody QuoteData quoteData) {
        log.info("Quote Data from the client ==> " + quoteData);

        return new Quote(1399, "RD1314234", true);
    }
}
