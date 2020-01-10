package com.api.dinnercontest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public LoginRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getEncodedPassword(String accessName) {

        //log.debug("Start getEncodedPassword for accessName {}", accessName);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("accessName", accessName);

        RowMapper<String> mapper = (rs, rowNum) -> {
            String password = rs.getString("password");
            return password;
        };

        String sql = "select u.password from users u where u.access_name = :accessName limit 1";

        String password = this.jdbcTemplate.query(sql, parameters, mapper).get(0);

        //log.debug("Encoded password found: {} ", password);

        return password;
    }

    public void setToken(String accessName, String token) {

        //log.debug("Start setToken for accessName {}", accessName);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("accessName", accessName);
        parameters.addValue("token", token);

        String sql = "insert into tokens (access_name, token) values (:accessName, :token)";

        this.jdbcTemplate.update(sql, parameters);

        //log.debug("Token saved: {} ", token);

    }
}
