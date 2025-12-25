package com.github.rusichpt.nimbus.exception;

import lombok.experimental.StandardException;

@StandardException
public class JsonConversionException extends RuntimeException {
    public JsonConversionException() {
        super("Ошибка сериализации запроса");
    }
}