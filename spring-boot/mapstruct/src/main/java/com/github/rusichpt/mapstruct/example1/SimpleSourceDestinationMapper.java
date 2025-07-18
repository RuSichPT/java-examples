package com.github.rusichpt.mapstruct.example1;

import org.mapstruct.Mapper;

@Mapper
public interface SimpleSourceDestinationMapper {
    SimpleDestination sourceToDestination(SimpleSource source);

    SimpleSource destinationToSource(SimpleDestination destination);
}
