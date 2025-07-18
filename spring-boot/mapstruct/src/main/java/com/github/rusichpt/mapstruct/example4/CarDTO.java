package com.github.rusichpt.mapstruct.example4;

import lombok.Data;

@Data
public class CarDTO {
    private int id;
    private String name;
    private FuelType fuelType;
}
