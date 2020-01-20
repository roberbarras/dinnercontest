package com.api.dinnercontest.repository;

import com.api.dinnercontest.controller.LoginController;
import com.api.dinnercontest.model.RestaurantModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
        parameters.addValue("date", restaurantModel.getDate());
        parameters.addValue("address", restaurantModel.getAddress());
        parameters.addValue("photo", restaurantModel.getPhoto());

        String sql = "insert into restaurant (name, host, date, address, photo) values (:name, :host, :date, :address, :photo)";

        this.jdbcTemplate.update(sql, parameters);

        log.info("Restaurant {} saved", restaurantModel.getName());
    }

}
