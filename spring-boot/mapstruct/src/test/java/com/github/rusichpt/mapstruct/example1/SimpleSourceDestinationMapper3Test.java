package com.github.rusichpt.mapstruct.example1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SimpleSourceDestinationMapper3Test {

    @Autowired
    SimpleSourceDestinationMapper3 mapper;
    @Autowired
    SimpleService service;

    @Test
    void givenSourceToDestination_whenMaps_thenCorrect() {
        SimpleSource simpleSource = new SimpleSource();
        simpleSource.setName("SourceName");
        simpleSource.setDescription("SourceDescription");

        SimpleDestination destination = mapper.sourceToDestination(simpleSource, service);

        assertEquals("enrich " + simpleSource.getName(), destination.getName());
        assertEquals(simpleSource.getDescription(), destination.getDescription());
    }
}