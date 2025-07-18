package com.github.rusichpt.mapstruct.example5;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(imports = java.util.UUID.class)
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "id", source = "id", defaultExpression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "name", source = "name", defaultValue = "anonymous")
    PersonDTO personToPersonDTO(Person person);
}
