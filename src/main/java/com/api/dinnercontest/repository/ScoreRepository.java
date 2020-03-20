package com.api.dinnercontest.repository;

import com.api.dinnercontest.controller.LoginController;
import com.api.dinnercontest.model.CategoryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class ScoreRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public void saveCategory(CategoryModel categoryModel) {
        log.debug("Start saving category {}", categoryModel.getCategoryName());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("group", categoryModel.getGroupId());
        parameters.addValue("name", categoryModel.getCategoryName());
        parameters.addValue("weighing", categoryModel.getWeighing());
        parameters.addValue("user", categoryModel.getUserId());
        parameters.addValue("date", LocalDateTime.now());

        String sql = "insert into categories (group_id, category_name, weighing, user_id, creation_date, removal_date) VALUES (:group, :name, :weighing, :user, :date, null)";

        this.jdbcTemplate.update(sql, parameters);

        log.debug("Category {} saved", categoryModel.getCategoryName());
    }

    public CategoryModel getCategory(Long id) {
        log.debug("Start getting category {}", id);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        RowMapper<CategoryModel> mapper = (rs, rowNum) -> {
            CategoryModel category = new CategoryModel();
            category.setCategoryName(rs.getString("category_name"));
            category.setWeighing(rs.getInt("Weighing"));

            return category;
        };

        String sql = "select category_name, weighing from categories where category_id = :id";

        CategoryModel categoryModel = this.jdbcTemplate.query(sql, parameters, mapper).get(0);

        log.debug("Category found: {} ", categoryModel.toString());

        return categoryModel;

    }

}
