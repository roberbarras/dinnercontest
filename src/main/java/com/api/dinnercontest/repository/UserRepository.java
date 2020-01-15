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
            user.setEmail(rs.getString("email"));
            user.setLastLogin(rs.getTimestamp("last_login").toLocalDateTime());

            return user;
        };

        String sql = "select u.user_name, u.access_name, u.creation_date, u.email, u.last_login, " +
                "u.local_privacy, u.global_privacy " +
                "from users u where u.user_id = :id " +
                "limit 1";

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
        parameters.addValue("email", userEntity.getEmail());
        parameters.addValue("last_login", LocalDateTime.of(2000, 1, 1, 0, 0));
        parameters.addValue("local_privacy", 0);
        parameters.addValue("global_privacy", 0);
        parameters.addValue("password", userEntity.getPassword());

        String sql = "insert into users (user_name, access_name, creation_date, " +
                "email, last_login, local_privacy, global_privacy, password) " +
                "values (:name, :access, :creation, :email, :last_login, :local_privacy, :global_privacy, :password)";

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

    public Integer isUsed(String user) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user", user);

        String sql = "select count(u.access_name) from users u where u.access_name = :user";

        return this.jdbcTemplate.queryForObject(sql, parameters, Integer.class);

    }
}
