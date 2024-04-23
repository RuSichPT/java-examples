package com.github.rusichpt.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmployeeDAO {

    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedTemplate;

    public Integer getCountOfEmployees() {
        String sql = "SELECT COUNT(*) FROM EMPLOYEE";
        return template.queryForObject(sql, Integer.class);
    }

    public int addEmployee(int id) {
        String sql = "INSERT INTO EMPLOYEE VALUES (?, ?, ?, ?)";
        return template.update(sql, id, "Bill", "Gates", "USA");
    }

    public String getFirstNameUsingMapSqlParameterSource(int id) {
        String sql = "SELECT FIRST_NAME FROM EMPLOYEE WHERE ID = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        return namedTemplate.queryForObject(sql, namedParameters, String.class);
    }

    public Integer getCountUsingBeanPropertySqlParameterSource(String firstName) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);

        String sql = "SELECT COUNT(*) FROM EMPLOYEE WHERE FIRST_NAME = :firstName";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(employee);

        return namedTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public Employee getEmployee(int id) {
        final String sql = "SELECT * FROM EMPLOYEE WHERE ID = ?";
        return template.queryForObject(sql, new EmployeeRowMapper(), id);
    }
}
