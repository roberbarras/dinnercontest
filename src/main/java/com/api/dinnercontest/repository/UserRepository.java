package com.api.dinnercontest.repository;

import com.api.dinnercontest.entity.UserEntity;
import com.api.dinnercontest.model.UserGroupModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Slf4j
@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserEntity findById(Long id) {

        //log.info("Start find user for id {}", id);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        RowMapper<UserEntity> mapper = (rs, rowNum) -> {
            UserEntity user = new UserEntity();
            user.setUserName(rs.getString("user_name"));
            user.setAccessName(rs.getString("access_name"));
            user.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
            return user;
        };

        String sql = "select users.user_name, users.access_name, users.creation_date from users where user_id = :id limit 1";

        UserEntity user = this.jdbcTemplate.query(sql, parameters, mapper).get(0);

        //log.info("User found: {} ", user.toString());

        return user;
    }

    public void save(UserEntity userEntity) {

        //log.info("Start save user with name {}", name);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", userEntity.getUserName());
        parameters.addValue("access", userEntity.getAccessName());
        parameters.addValue("creation", LocalDateTime.now());

        String sql = "insert into users (user_name, access_name, creation_date) values (:name, :access, :creation)";

        int saved = jdbcTemplate.update(sql, parameters);

        //log.info("Users saved: {} ", saved);

    }

    public void join(UserGroupModel userGroupModel) {

        //log.info("Start joining group");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user", userGroupModel.getUserId());
        parameters.addValue("group", userGroupModel.getGroupId());

        String sql = "insert into user_group (\"user\", \"group\") VALUES (:user, :group)";

        int saved = jdbcTemplate.update(sql, parameters);

        //log.info("User joined: {} ", userGroupModel);

    }
}
