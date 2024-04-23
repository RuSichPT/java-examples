package com.github.rusichpt.filter.controller.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxiRide {

    private Boolean isNightSurcharge;
    private Long distanceInMile;

}
