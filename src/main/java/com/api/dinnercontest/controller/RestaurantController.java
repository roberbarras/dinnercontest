package com.api.dinnercontest.controller;

import com.api.dinnercontest.model.RestaurantModel;
import com.api.dinnercontest.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestaurantController {

    private final RestaurantService restaurantService;

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurant")
    public ResponseEntity<RestaurantModel> postRestaurant(HttpServletRequest request, @RequestBody RestaurantModel restaurantModel) {
        log.info("[REQUEST RECEIVED    -    POST    /restaurant    {}]", restaurantModel.getName());
        restaurantService.saveRestaurant(restaurantModel);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri());
        return new ResponseEntity<>(restaurantModel, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantModel> getRestaurant(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurant(id));
    }
}
