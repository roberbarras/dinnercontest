package com.api.dinnercontest.repository;

import com.api.dinnercontest.controller.LoginController;
import com.api.dinnercontest.model.UserTokenModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public LoginRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getEncodedPassword(String accessName) {

        log.debug("Start getEncodedPassword for accessName {}", accessName);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("accessName", accessName);

        RowMapper<String> mapper = (rs, rowNum) -> {
            String password = rs.getString("password");
            return password;
        };

        String sql = "select u.password from users u where u.access_name = :accessName limit 1";

        String password = this.jdbcTemplate.query(sql, parameters, mapper).get(0);

        log.debug("Encoded password found: {} ", password);

        return password;
    }

    public void setToken(Long userId, String accessName, String token) {

        log.debug("Start setToken for accessName {}", accessName);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("userId", userId);
        parameters.addValue("accessName", accessName);
        parameters.addValue("token", token);

        String sql = "insert into tokens (user_id, access_name, token) values (:userId, :accessName, :token)";

        this.jdbcTemplate.update(sql, parameters);

        log.debug("Token saved: {} ", token);

    }

    public int checkToken(UserTokenModel userTokenModel) {

        log.debug("Start checkToken for accessName {}", userTokenModel.getAccessName());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("accessName", userTokenModel.getAccessName());
        parameters.addValue("token", userTokenModel.getToken());

        String sql = "select count(*) from tokens t where t.access_name = :accessName and t.token = :token";

        Integer results = this.jdbcTemplate.queryForObject(sql, parameters, Integer.class);

        log.debug("results: {} ", results);

        return results != null ? results.intValue() : 0;
    }

    public int checkIdToken(Long userId, String token) {

        log.debug("Start checkToken for accessName {}", userId);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", userId);
        parameters.addValue("token", token);

        String sql = "select count(*) from tokens t where t.user_id = :id and t.token = :token";

        Integer results = this.jdbcTemplate.queryForObject(sql, parameters, Integer.class);

        log.debug("results: {} ", results);

        return results != null ? results.intValue() : 0;
    }
}
