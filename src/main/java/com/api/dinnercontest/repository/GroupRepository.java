package com.api.dinnercontest.repository;

import com.api.dinnercontest.entity.GroupEntity;
import com.api.dinnercontest.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class GroupRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public GroupRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public GroupEntity findById(Long id) {

        //log.info("Start find group for id {}", id);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        RowMapper<GroupEntity> mapper = (rs, rowNum) -> {
            GroupEntity group = new GroupEntity();
            group.setGroupName(rs.getString("group_name"));
            return group;
        };

        String sql = "select groups.group_name from groups where group_id = :id limit 1";

        GroupEntity group = jdbcTemplate.query(sql, parameters, mapper).get(0);

        //log.info("Groups found: {} ", group.toString());

        return group;
    }

    public void save(String name) {

        //log.info("Start save group with name {}", name);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);

        RowMapper<GroupEntity> mapper = (rs, rowNum) -> {
            GroupEntity group = new GroupEntity();
            group.setGroupName(rs.getString("name"));
            return group;
        };

        String sql = "insert into groups (group_name) values (:name)";

        int saved = jdbcTemplate.update(sql, parameters);

        //log.info("Groups saved: {} ", saved);

    }

    public List<UserModel> findByIdWithUsers(Long id) {

        //log.info("Start find group and users for id_group {}", id);

        List<UserModel> group;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        RowMapper<UserModel> mapper = (rs, rowNum) -> {
            UserModel user = new UserModel();
            user.setUserName(rs.getString("user_name"));
            user.setAccessName(rs.getString("access_name"));
            return user;
        };

        String sql = "select user_name from users join user_group on user_id = \"user\" join groups on group_id = \"group\" where group_id = :id";
        group = jdbcTemplate.query(sql, parameters, mapper);

        //log.info("Users found for group {}: {} ", id,  group.toString());

        return group;

    }


}
