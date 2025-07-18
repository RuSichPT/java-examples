package com.github.rusichpt.mapstruct.example2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmployeeMapperTest {
    @Autowired
    EmployeeMapper mapper;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @Test
    void givenEmployeeDTOWithDiffNameToEmployee_whenMaps_thenCorrect() throws ParseException {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(1);
        dto.setEmployeeName("John");
        dto.setDivision(new DivisionDTO(1, "Division1"));
        dto.setEmployeeStartDt("01-04-2016 01:00:00");

        Employee entity = mapper.toEntity(dto);

        assertEquals(dto.getEmployeeId(), entity.getId());
        assertEquals(dto.getEmployeeName(), entity.getName());

        assertEquals(dto.getDivision().getId(), entity.getDivision().getId());
        assertEquals(dto.getDivision().getName(), entity.getDivision().getName());

        assertEquals(format.parse(dto.getEmployeeStartDt()).toString(), entity.getStartDt().toString());
    }

    @Test
    void givenEmployeeWithDiffNameToEmployeeDTO_whenMaps_thenCorrect() throws ParseException {
        Employee entity = new Employee();
        entity.setId(1);
        entity.setName("John");
        entity.setDivision(new Division(1, "Division1"));
        entity.setStartDt(new Date());

        EmployeeDTO dto = mapper.toDto(entity);

        assertEquals(dto.getEmployeeId(), entity.getId());
        assertEquals(dto.getEmployeeName(), entity.getName());

        assertEquals(dto.getDivision().getId(), entity.getDivision().getId());
        assertEquals(dto.getDivision().getName(), entity.getDivision().getName());

        assertEquals(format.parse(dto.getEmployeeStartDt()).toString(), entity.getStartDt().toString());
    }
}