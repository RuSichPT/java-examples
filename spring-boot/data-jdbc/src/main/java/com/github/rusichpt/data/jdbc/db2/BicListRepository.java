package com.github.rusichpt.data.jdbc.db2;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BicListRepository {
    @Qualifier("db2JdbcTemplate")
    private final NamedParameterJdbcTemplate db2JdbcTemplate;

    private static final String SELECT_BIC_LIST =
            "select id, bic, bank_name, date_in, date_out " +
            "from bic_list where id = :id";

    public Optional<BicListEntity> findById(@Param("id") Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        return db2JdbcTemplate.query(SELECT_BIC_LIST, params, BeanPropertyRowMapper.newInstance(BicListEntity.class))
                .stream()
                .findFirst();
    }
}
