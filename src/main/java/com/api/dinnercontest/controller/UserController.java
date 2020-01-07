package com.api.dinnercontest.controller;

import com.api.dinnercontest.model.UserGroupModel;
import com.api.dinnercontest.model.UserModel;
import com.api.dinnercontest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/v1")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getUser(HttpServletRequest request, @PathVariable(value = "id") Long id) {
        //log.info("[REQUEST RECEIVED    -    GET    /user/{}]", id);
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity postUser(HttpServletRequest request, @RequestBody UserModel userModel) {
        //log.info("[REQUEST RECEIVED    -    POST    /user    {}]", userModel.getName());
        userService.save(userModel);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri());
        return new ResponseEntity<>(userModel, httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/join-group")
    public ResponseEntity joinGroup(HttpServletRequest request, @RequestBody UserGroupModel userGroupModel) {
        //log.info("[REQUEST RECEIVED    -    POST    /join-group    user {} joined group {}]", userGroupModel.getUserId(), userGroupModel.getGroupId());
        userService.joinGroup(userGroupModel);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri());
        return new ResponseEntity<>(userGroupModel, httpHeaders, HttpStatus.CREATED);
    }
}