package com.github.rusichpt.grafana.loki.tempo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    private long productId;
    private double priceAmount;
    private double discount;
}
