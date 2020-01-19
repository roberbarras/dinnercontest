package com.api.dinnercontest.repository;

import com.api.dinnercontest.controller.LoginController;
import com.api.dinnercontest.model.CategoryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public void saveCategory(CategoryModel categoryModel) {
        log.info("Start saving category {}", categoryModel.getCategoryName());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", categoryModel.getCategoryName());
        parameters.addValue("weighing", categoryModel.getWeighing());

        String sql = "insert into category (category_name, weighing) VALUES (:name, :weighing)";

        this.jdbcTemplate.update(sql, parameters);

        log.info("Category {} saved", categoryModel.getCategoryName());
    }

}
