package com.github.rusichpt.mapstruct.example4;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarMapperTest {
    CarMapper mapper = Mappers.getMapper(CarMapper.class);

    @Test
    void testGivenSubTypeElectric_mapsModifiedFieldsToSuperTypeDto_whenBeforeAndAfterMappingMethodsCarCalled() {
        Car car = new ElectricCar();
        car.setId(12);
        car.setName("Tesla_Model_C");

        CarDTO carDto = mapper.toCarDto(car);

        assertEquals("TESLA_MODEL_C", carDto.getName());
        assertEquals(FuelType.ELECTRIC, carDto.getFuelType());
    }

    @Test
    void testGivenSubTypeBioDiesel_mapsModifiedFieldsToSuperTypeDto_whenBeforeAndAfterMappingMethodsCarCalled() {
        Car car = new BioDieselCar();
        car.setId(11);
        car.setName("Tesla_Model_X");

        CarDTO carDto = mapper.toCarDto(car);

        assertEquals("TESLA_MODEL_X", carDto.getName());
        assertEquals(FuelType.BIO_DIESEL, carDto.getFuelType());
    }
}