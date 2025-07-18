package com.github.rusichpt.mapstruct.example2;

import lombok.Data;

@Data
public class EmployeeDTO {
    private int employeeId;
    private String employeeName;
    private DivisionDTO division;
    private String employeeStartDt;
}
