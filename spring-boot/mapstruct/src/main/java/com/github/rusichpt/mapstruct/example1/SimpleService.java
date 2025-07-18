package com.github.rusichpt.mapstruct.example1;

import org.mapstruct.Named;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

    public String enrichName(String name) {
        return "enrich " + name;
    }

    @Named("enrichName")
    public String enrichName2(String name) {
        return "enrichName " + name;
    }
}
