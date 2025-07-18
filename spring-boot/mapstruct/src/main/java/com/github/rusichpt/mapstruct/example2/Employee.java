package com.github.rusichpt.mapstruct.example2;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private int id;
    private String name;
    private Division division;
    private Date startDt;
}
