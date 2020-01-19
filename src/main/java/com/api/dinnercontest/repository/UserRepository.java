package com.api.dinnercontest.repository;

import com.api.dinnercontest.controller.LoginController;
import com.api.dinnercontest.model.UserGroupModel;
import com.api.dinnercontest.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserModel findById(Long id) {

        log.info("Start find user for id {}", id);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        RowMapper<UserModel> mapper = (rs, rowNum) -> {
            UserModel user = new UserModel();
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

        UserModel user = this.jdbcTemplate.query(sql, parameters, mapper).get(0);

        log.info("User found: {} ", user.toString());

        return user;
    }

    public void save(UserModel userModel) {

        log.info("Start save user with name {}", userModel.getAccessName());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", userModel.getUserName());
        parameters.addValue("access", userModel.getAccessName());
        parameters.addValue("creation", LocalDateTime.now());
        parameters.addValue("email", userModel.getEmail());
        parameters.addValue("last_login", LocalDateTime.of(2000, 1, 1, 0, 0));
        parameters.addValue("local_privacy", 0);
        parameters.addValue("global_privacy", 0);
        parameters.addValue("password", userModel.getPassword());

        String sql = "insert into users (user_name, access_name, creation_date, " +
                "email, last_login, local_privacy, global_privacy, password) " +
                "values (:name, :access, :creation, :email, :last_login, :local_privacy, :global_privacy, :password)";

        int saved = jdbcTemplate.update(sql, parameters);

        log.info("Users saved: {} ", saved);

    }

    public void join(UserGroupModel userGroupModel) {

        log.info("Start joining group");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user", userGroupModel.getUserId());
        parameters.addValue("group", userGroupModel.getGroupId());
        parameters.addValue("creation", LocalDateTime.now());

        String sql = "insert into user_group (\"user\", \"group\", creation_date, valid) VALUES (:user, :group, :creation, true)";

        int saved = jdbcTemplate.update(sql, parameters);

        log.info("User joined: {} ", userGroupModel);

    }

    public Integer isUsed(String user) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user", user);

        String sql = "select count(u.access_name) from users u where u.access_name = :user";

        return this.jdbcTemplate.queryForObject(sql, parameters, Integer.class);

    }

    public void disjoin(UserGroupModel userGroupModel) {

        log.info("Start disjoining group");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user", userGroupModel.getUserId());
        parameters.addValue("group", userGroupModel.getGroupId());
        parameters.addValue("removalDate", LocalDateTime.now());

        String sql = "UPDATE user_group SET removal_date = :removalDate, valid = false WHERE \"user\" = :user AND \"group\" = :group";

        this.jdbcTemplate.update(sql, parameters);

        log.info("User disjoined: {} ", userGroupModel);
    }
}
