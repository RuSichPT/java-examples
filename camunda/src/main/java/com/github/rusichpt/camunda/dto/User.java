package com.github.rusichpt.camunda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@FieldNameConstants
public class User {
    private String name;
    private int age;
    private double salary;
}
