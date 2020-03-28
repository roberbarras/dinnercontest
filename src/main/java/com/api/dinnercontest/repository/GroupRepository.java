package com.api.dinnercontest.repository;

import com.api.dinnercontest.controller.LoginController;
import com.api.dinnercontest.model.CategoryModel;
import com.api.dinnercontest.model.GroupModel;
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
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class GroupRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public GroupRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public GroupModel findById(Long id) {

        log.debug("Start find group for id {}", id);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        RowMapper<GroupModel> mapper = (rs, rowNum) -> {
            GroupModel group = new GroupModel();
            group.setGroupName(rs.getString("group_name"));
            return group;
        };

        String sql = "select groups.group_name from groups where group_id = :id limit 1";

        GroupModel group = jdbcTemplate.query(sql, parameters, mapper).get(0);

        log.debug("Groups found: {} ", group.toString());

        return group;
    }

    public void save(String name) {

        log.debug("Start save group with name {}", name);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        parameters.addValue("creation", LocalDateTime.now());

        RowMapper<GroupModel> mapper = (rs, rowNum) -> {
            GroupModel group = new GroupModel();
            group.setGroupName(rs.getString("name"));
            return group;
        };

        String sql = "insert into groups (group_name, creation_date) values (:name, :creation)";

        int saved = jdbcTemplate.update(sql, parameters);

        log.debug("Groups saved: {} ", saved);

    }

    public List<UserModel> findByIdWithUsers(Long id) {

        log.debug("Start find group and users for id_group {}", id);

        List<UserModel> group;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        RowMapper<UserModel> mapper = (rs, rowNum) -> {
            UserModel user = new UserModel();
            user.setUserName(rs.getString("user_name"));
            user.setUserId(rs.getLong("user_id"));
            user.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
            user.setLastLogin(rs.getTimestamp("last_login").toLocalDateTime());
            user.setEmail(rs.getString("email"));
            return user;
        };

        String sql = "select user_name, user_id, \"users\".creation_date, last_login, email from users join user_group on user_id = \"user\" join groups on group_id = \"group\" where group_id = :id";
        group = jdbcTemplate.query(sql, parameters, mapper);

        log.debug("Users found for group {}: {} ", id, group.toString());

        return group;

    }

    public List<CategoryModel> getCategories(Long groupId) {
        log.debug("Start getting category for group {}", groupId);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("group", groupId);
        parameters.addValue("date", LocalDateTime.now());

        RowMapper<CategoryModel> mapper = (rs, rowNum) -> {
            CategoryModel category = new CategoryModel();
            category.setCategoryId(rs.getLong("category_id"));
            category.setCategoryName(rs.getString("category_name"));
            category.setWeighing(rs.getInt("weighing"));

            return category;
        };

        String sql = "select * from categories where group_id = :group and removal_date is null or removal_date > :date order by weighing desc";

        List<CategoryModel> categoryList = this.jdbcTemplate.query(sql, parameters, mapper);

        log.debug("Categories found: {} ", categoryList.size());

        return categoryList;
    }

    public List<Long> getIdCategories(Long group) {
        log.debug("Start getting ids categories for group {}", group);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("group", group);
        parameters.addValue("date", LocalDateTime.now());

        String sql = "select category_id from categories where group_id = :group and removal_date is null or removal_date > :date order by category_id asc";

        return this.jdbcTemplate.queryForList(sql, parameters, Long.class);
    }

    public boolean checkUser(Long group, Long user) {
        log.debug("Start checking if the user {} belongs to the group {}", user, group);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("group", group);
        parameters.addValue("user", user);

        String sql = "select count(*) from user_group ug where ug.user = :user and ug.group = :group";
        int count = Optional.ofNullable(jdbcTemplate.queryForObject(sql, parameters, Integer.class)).orElse(0);

        log.debug("Categories found for group {}", group);

        return count > 0;
    }

    public List<GroupModel> getGroupUser(Long user) {
        log.debug("Start getting groups for use {}", user);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user", user);

        RowMapper<GroupModel> mapper = (rs, rowNum) -> {
            GroupModel group = new GroupModel();
            group.setGroupId(rs.getLong("group_id"));
            group.setGroupName(rs.getString("group_name"));
            group.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());

            return group;
        };

        String sql = "select group_id, group_name, groups.creation_date from user_group join groups on user_group.\"group\" = groups.group_id where \"user\" = :user";

        List<GroupModel> groupModelList = this.jdbcTemplate.query(sql, parameters, mapper);

        log.debug("Groups found: {} ", groupModelList.size());

        return groupModelList;
    }

    public Long getGroupOfRestaurant(Long restaurant) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("restaurant", restaurant);

        String sql = "select id_group from restaurants where id_restaurant = :restaurant";

        return this.jdbcTemplate.queryForObject(sql, parameters, Long.class);
    }
}
