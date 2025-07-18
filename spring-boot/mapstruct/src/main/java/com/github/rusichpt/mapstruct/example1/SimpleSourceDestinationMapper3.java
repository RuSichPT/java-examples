package com.github.rusichpt.mapstruct.example1;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface SimpleSourceDestinationMapper3 {
    @Mapping(target = "name", expression = "java(service.enrichName(source.getName()))")
    SimpleDestination sourceToDestination(SimpleSource source, @Context SimpleService service);
}
