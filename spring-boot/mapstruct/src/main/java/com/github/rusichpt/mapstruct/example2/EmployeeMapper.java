package com.github.rusichpt.mapstruct.example2;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface EmployeeMapper {

    @Mapping(target = "employeeId", source = "id")
    @Mapping(target = "employeeName", source = "name")
    @Mapping(target = "employeeStartDt", source = "startDt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    EmployeeDTO toDto(Employee entity);

    @Mapping(target = "id", source = "employeeId")
    @Mapping(target = "name", source = "employeeName")
    @Mapping(target = "startDt", source = "employeeStartDt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    Employee toEntity(EmployeeDTO dto);

    DivisionDTO divisionToDivisionDTO(Division entity);

    Division divisionDTOtoDivision(DivisionDTO dto);
}
