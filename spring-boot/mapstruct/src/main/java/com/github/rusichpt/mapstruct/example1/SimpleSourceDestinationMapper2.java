package com.github.rusichpt.mapstruct.example1;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class SimpleSourceDestinationMapper2 {

    @Autowired // Лучше обойтись без внедрения и абстрактного класса смотри SimpleSourceDestinationMapper3
    protected SimpleService simpleService;

    @Mapping(target = "name", expression = "java(simpleService.enrichName(source.getName()))")
    public abstract SimpleDestination sourceToDestination(SimpleSource source);
}
