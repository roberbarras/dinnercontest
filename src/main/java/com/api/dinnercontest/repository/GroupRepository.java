package com.api.dinnercontest.repository;

import com.api.dinnercontest.controller.LoginController;
import com.api.dinnercontest.model.CategoryModel;
import com.api.dinnercontest.model.GroupCategoryModel;
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
            user.setAccessName(rs.getString("access_name"));
            return user;
        };

        String sql = "select user_name from users join user_group on user_id = \"user\" join groups on group_id = \"group\" where group_id = :id";
        group = jdbcTemplate.query(sql, parameters, mapper);

        log.debug("Users found for group {}: {} ", id, group.toString());

        return group;

    }

    public void saveCategory(GroupCategoryModel groupCategoryModel) {

        log.debug("Start save Group Category");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("group", groupCategoryModel.getGroupId());
        parameters.addValue("category", groupCategoryModel.getCategoryId());
        parameters.addValue("user", groupCategoryModel.getUserId());
        parameters.addValue("date", LocalDateTime.now());

        String sql = "insert into group_category (id_group, id_category, user_id, creation_date) values (:group, :category, :user, :date)";

        this.jdbcTemplate.update(sql, parameters);

        log.debug("Category group relation saved");
    }

    public List<Long> getIdCategories(Long group) {

        log.debug("Start find id categories of group {}", group);

        List<Long> categories;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("group", group);


        String sql = "select id_category from group_category where id_group = :group";
        categories = jdbcTemplate.queryForList(sql, parameters, Long.class);

        log.debug("Categories found for group {}", group);

        return categories;
    }

    public List<CategoryModel> getCategories(Long group) {

        log.debug("Start find categories of group {}", group);

        List<CategoryModel> categories;

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("group", group);

        RowMapper<CategoryModel> mapper = (rs, rowNum) -> {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setCategoryId(rs.getLong("id_category"));
            categoryModel.setCategoryName(rs.getString("category_name"));
            categoryModel.setWeighing(rs.getInt("weighing"));
            return categoryModel;
        };

        String sql = "select category.id_category, category.category_name, category.weighing " +
                "from group_category inner join category " +
                "on category.id_category = group_category.id_category " +
                "where id_group = :group " +
                "order by weighing desc";
        categories = jdbcTemplate.query(sql, parameters, mapper);

        log.debug("Categories found for group {}: {}", group, categories.toArray());

        return categories;
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
}
