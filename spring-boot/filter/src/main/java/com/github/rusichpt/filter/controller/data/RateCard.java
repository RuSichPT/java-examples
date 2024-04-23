package com.github.rusichpt.filter.controller.data;

import lombok.Data;

@Data
public class RateCard {

    private String nightSurcharge;
    private String ratePerMile;

    public RateCard() {
        nightSurcharge = "Extra $ 100";
        ratePerMile = "$ 10 Per Mile";
    }

}