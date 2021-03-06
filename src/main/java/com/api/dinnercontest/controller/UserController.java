package com.api.dinnercontest.controller;

import com.api.dinnercontest.model.UserGroupModel;
import com.api.dinnercontest.model.UserModel;
import com.api.dinnercontest.service.LoginService;
import com.api.dinnercontest.service.UserService;
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
public class UserController {

    private UserService userService;

    private LoginService loginService;

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserModel> getUser(HttpServletRequest request, @PathVariable(value = "id") Long id) {
        log.info("[REQUEST RECEIVED    -    GET    /user/{}]", id);
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Boolean> isUsed(@RequestParam String name) {
        log.info("[REQUEST RECEIVED    -    GET    /user {}]", name);
        return ResponseEntity.ok().body(userService.isUsed(name));
    }

    @PostMapping("/user")
    public ResponseEntity<UserModel> postUser(HttpServletRequest request, @RequestBody UserModel userModel) {
        log.info("[REQUEST RECEIVED    -    POST    /user    {}]", userModel.getAccessName());
        userService.save(userModel);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri());
        userModel.setPassword("");
        return new ResponseEntity<>(userModel, httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/join-group")
    public ResponseEntity<UserGroupModel> joinGroup(HttpServletRequest request, @RequestBody UserGroupModel userGroupModel) {
        log.info("[REQUEST RECEIVED    -    POST    /join-group    user {} joined group {}]", userGroupModel.getUserId(), userGroupModel.getGroupId());
        if (loginService.checkIdToken(userGroupModel.getUserId(), userGroupModel.getToken())) {
            userService.joinGroup(userGroupModel);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri());
            return new ResponseEntity<>(userGroupModel, httpHeaders, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/disjoin-group")
    public ResponseEntity<UserGroupModel> disjoinGroup(HttpServletRequest request, @RequestBody UserGroupModel userGroupModel) {
        log.info("[REQUEST RECEIVED    -    POST    /disjoin-group    user {} disjoined group {}]", userGroupModel.getUserId(), userGroupModel.getGroupId());
        userService.disjoinGroup(userGroupModel);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri());
        return new ResponseEntity<>(userGroupModel, httpHeaders, HttpStatus.OK);
    }
}