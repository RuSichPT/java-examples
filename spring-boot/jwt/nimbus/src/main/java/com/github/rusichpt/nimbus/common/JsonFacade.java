package com.github.rusichpt.nimbus.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rusichpt.nimbus.exception.JsonConversionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
@RequiredArgsConstructor
public class JsonFacade {
    private final ObjectMapper objectMapper;

    public String writeValueAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonConversionException();
        }
    }

    public <T> T readValue(String json, Class<T> tClass) {
        return readValue(json, new TypeReference<>() {
            @Override
            public Type getType() {
                return tClass;
            }
        });
    }

    public <T> T readValue(String json, TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(json, valueTypeRef);
        } catch (JsonProcessingException e) {
            throw new JsonConversionException();
        }
    }


}