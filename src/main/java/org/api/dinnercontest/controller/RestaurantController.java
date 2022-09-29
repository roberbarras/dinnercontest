package org.api.dinnercontest.controller;

import lombok.extern.slf4j.Slf4j;
import org.api.dinnercontest.model.RestaurantModel;
import org.api.dinnercontest.service.GroupService;
import org.api.dinnercontest.service.LoginService;
import org.api.dinnercontest.service.RestaurantService;
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

    public RestaurantController(RestaurantService restaurantService, LoginService loginService, GroupService groupService) {
        this.restaurantService = restaurantService;
        this.loginService = loginService;
        this.groupService = groupService;
    }

    @PostMapping("/restaurant")
    public ResponseEntity<RestaurantModel> postRestaurant(
            @RequestHeader(name = "user", required = false) Long user,
            @RequestHeader(name = "token", required = false) String token,
            @RequestBody RestaurantModel restaurantModel) {
//        log.info("[REQUEST RECEIVED    -    POST    /restaurant    {}]", restaurantModel.getName());
        if (loginService.checkIdToken(user, token)
                && groupService.checkUserGroup(restaurantModel.getGroup(), user)) {
            restaurantService.saveRestaurant(restaurantModel);
            return new ResponseEntity<>(restaurantModel, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantModel> getRestaurant(@PathVariable(value = "id") Long id) {
//        log.info("[REQUEST RECEIVED    -    GET     /restaurant    {}]", id);
        return ResponseEntity.ok(restaurantService.getRestaurant(id));
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantModel> deleteRestaurant(
            @RequestHeader(name = "user", required = false) Long user,
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable(value = "id") Long restaurant) {
//        log.info("[REQUEST RECEIVED    -    DELETE  /restaurant    {}]", restaurant);
        if (loginService.checkIdToken(user, token)
                && restaurantService.checkUserRestaurant(restaurant, user)) {
            restaurantService.deleteRestaurant(restaurant);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}