package com.github.rusichpt.dataspringbootstarter.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "data")
@Data
public class DataProperties {
    private String text = "Hello world";
}
