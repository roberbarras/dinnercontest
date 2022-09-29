package org.api.dinnercontest.repository;

import lombok.extern.slf4j.Slf4j;
import org.api.dinnercontest.controller.LoginController;
import org.api.dinnercontest.model.AssessmentModel;
import org.api.dinnercontest.model.CategoryModel;
import org.api.dinnercontest.model.ScoreModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Slf4j
@Repository
public class ScoreRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void saveCategory(CategoryModel categoryModel, Long user) {
//        log.debug("Start saving category {}", categoryModel.getCategoryName());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("group", categoryModel.getGroupId());
        parameters.addValue("name", categoryModel.getCategoryName());
        parameters.addValue("weighing", categoryModel.getWeighing());
        parameters.addValue("user", user);
        parameters.addValue("date", LocalDateTime.now());

        String sql = "insert into categories (group_id, category_name, weighing, user_id, creation_date, removal_date) values (:group, :name, :weighing, :user, :date, null)";

        this.jdbcTemplate.update(sql, parameters);

//        log.debug("Category {} saved", categoryModel.getCategoryName());
    }

    public CategoryModel getCategory(Long id) {
//        log.debug("Start getting category {}", id);

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

//        log.debug("Category found: {} ", categoryModel.toString());

        return categoryModel;

    }

    public void deleteCategory(Long id, Long user) {
//        log.debug("Start deleting category {}", id);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        parameters.addValue("date", LocalDateTime.now());

        String sql = "update categories set removal_date = :date where category_id = :id";

        this.jdbcTemplate.update(sql, parameters);

//        log.debug("Category {} deleted", id);
    }

    public void saveAssessment(AssessmentModel assessmentModel) {
//        log.debug("Start saving assessment by user {}", assessmentModel.getUser());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("restaurant", assessmentModel.getRestaurant());
        parameters.addValue("user", assessmentModel.getUser());
        parameters.addValue("date", LocalDateTime.now());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "insert into assessments (\"user\", restaurant, creation_date, total_score, votes) values (:user, :restaurant, :date, 0, 0)";

        this.jdbcTemplate.update(sql, parameters, keyHolder);

        Long assessmentId = (Long) keyHolder.getKeys().get("assessment_id");

        for (ScoreModel score : assessmentModel.getAssessment()) {
            score.setAssessmentId(assessmentId);
            saveScore(score);
        }

        //TODO recalcular puntuación

//        log.debug("Category {} saved", keyHolder.getKeys().get("assessment_id"));
    }

    private void saveScore(ScoreModel scoreModel) {
//        log.debug("Start saving score: category {}, value {}", scoreModel.getCategoryId(), scoreModel.getValue());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("assessment", scoreModel.getAssessmentId());
        parameters.addValue("value", scoreModel.getValue());
        parameters.addValue("category", scoreModel.getCategoryId());

        String sql = "insert into scores (assessment, \"value\", category) values (:assessment, :value, :category)";

        this.jdbcTemplate.update(sql, parameters);
    }
}
