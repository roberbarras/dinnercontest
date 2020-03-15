package com.api.dinnercontest.controller;

import com.api.dinnercontest.model.RestaurantModel;
import com.api.dinnercontest.service.GroupService;
import com.api.dinnercontest.service.LoginService;
import com.api.dinnercontest.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestaurantController {

    private RestaurantService restaurantService;

    private LoginService loginService;

    private GroupService groupService;

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    public RestaurantController(RestaurantService restaurantService, LoginService loginService, GroupService groupService) {
        this.restaurantService = restaurantService;
        this.loginService = loginService;
        this.groupService = groupService;
    }

    @PostMapping("/restaurant")
    public ResponseEntity<RestaurantModel> postRestaurant(@RequestBody RestaurantModel restaurantModel) {
        log.info("[REQUEST RECEIVED    -    POST    /restaurant    {}]", restaurantModel.getName());
        if (loginService.checkIdToken(restaurantModel.getUserId(), restaurantModel.getToken())
                && groupService.checkUserGroup(restaurantModel.getGroup(), restaurantModel.getUserId())) {
            restaurantService.saveRestaurant(restaurantModel);
            return new ResponseEntity<>(restaurantModel, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantModel> getRestaurant(@PathVariable(value = "id") Long id) {
        log.info("[REQUEST RECEIVED    -    GET    /restaurant    {}]", id);
        return ResponseEntity.ok(restaurantService.getRestaurant(id));
    }
}
