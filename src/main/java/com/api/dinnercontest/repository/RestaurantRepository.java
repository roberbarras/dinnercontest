package com.api.dinnercontest.repository;

import com.api.dinnercontest.controller.LoginController;
import com.api.dinnercontest.model.RestaurantModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class RestaurantRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public RestaurantRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveRestaurant(RestaurantModel restaurantModel) {

        log.info("Start save Restaurant");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", restaurantModel.getName());
        parameters.addValue("host", restaurantModel.getHost());
        parameters.addValue("group", restaurantModel.getGroup());
        parameters.addValue("date", Optional.ofNullable(restaurantModel.getDate()).orElse(LocalDateTime.now()));
        parameters.addValue("address", restaurantModel.getAddress());
        parameters.addValue("photo", restaurantModel.getPhoto());
        parameters.addValue("visible", restaurantModel.isVisible());
        parameters.addValue("creation_date", LocalDateTime.now());
        parameters.addValue("visible_date", restaurantModel.getVisibleDate());

        String sql = "insert into restaurants (name, host, id_group, date, address, photo, visible, creation_date, visible_date) " +
                "values (:name, :host, :group, :date, :address, :photo, :visible, :creation_date, :visible_date)";

        this.jdbcTemplate.update(sql, parameters);

        log.info("Restaurant {} saved", restaurantModel.getName());
    }

    public RestaurantModel getRestaurant(Long id) {
        log.info("Start find restaurant with id {}", id);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        RowMapper<RestaurantModel> mapper = (rs, rowNum) -> {
            RestaurantModel restaurant = new RestaurantModel();
            restaurant.setName(rs.getString("name"));
            restaurant.setHost(rs.getLong("host"));
            restaurant.setGroup(rs.getLong("id_group"));
            restaurant.setDate(rs.getTimestamp("date").toLocalDateTime());
            restaurant.setAddress(rs.getString("address"));
            restaurant.setPhoto(rs.getString("photo"));
            restaurant.setVisible(rs.getBoolean("visible"));
            restaurant.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
            restaurant.setVisibleDate(rs.getTimestamp("visible_date").toLocalDateTime());
            return restaurant;
        };

        String sql = "select name, host, id_group, date, address, photo, visible, creation_date, visible_date " +
                "from restaurants " +
                "where id_restaurant = :id";
        return jdbcTemplate.query(sql, parameters, mapper).get(0);
    }
}
