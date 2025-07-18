package com.github.rusichpt.mapstruct.example5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonMapperTest {

    @Test
    void givenPersonEntityToPersonWithExpression_whenMaps_thenCorrect() {
        Person entity = new Person();
        entity.setName("Micheal");

        PersonDTO personDto = PersonMapper.INSTANCE.personToPersonDTO(entity);

        assertNull(entity.getId());
        assertNotNull(personDto.getId());
        assertEquals(personDto.getName(), entity.getName());
    }

    @Test
    void givenPersonEntityWithNullName_whenMaps_thenCorrect() {
        Person entity = new Person();
        entity.setId("1");

        PersonDTO personDto = PersonMapper.INSTANCE.personToPersonDTO(entity);

        assertEquals("anonymous", personDto.getName());
    }

}