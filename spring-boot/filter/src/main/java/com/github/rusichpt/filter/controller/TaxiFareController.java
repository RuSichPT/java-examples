package com.github.rusichpt.filter.controller;

import com.github.rusichpt.filter.controller.data.RateCard;
import com.github.rusichpt.filter.controller.data.TaxiRide;
import com.github.rusichpt.filter.service.TaxiFareCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaxiFareController {
    private final TaxiFareCalculatorService taxiFareCalculatorService;

    @GetMapping("/taxifare/get/")
    public RateCard getTaxiFare() {
        return new RateCard();
    }

    @PostMapping("/taxifare/calculate/")
    public String calculateTaxiFare(@RequestBody TaxiRide taxiRide) {
        return taxiFareCalculatorService.calculateFare(taxiRide);
    }
}
