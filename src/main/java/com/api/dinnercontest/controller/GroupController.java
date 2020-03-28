package com.api.dinnercontest.controller;

import com.api.dinnercontest.model.CategoryModel;
import com.api.dinnercontest.model.GroupModel;
import com.api.dinnercontest.model.UserModel;
import com.api.dinnercontest.service.GroupService;
import com.api.dinnercontest.service.LoginService;
import com.api.dinnercontest.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class GroupController {

    private GroupService groupService;

    private LoginService loginService;

    private NotificationService notificationService;

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    public GroupController(GroupService groupService, LoginService loginService, NotificationService notificationService) {
        this.groupService = groupService;
        this.loginService = loginService;
        this.notificationService = notificationService;
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<GroupModel> getGroup(@PathVariable(value = "id") Long id) {
        log.info("[REQUEST RECEIVED    -    GET     /group/{}]", id);
        return new ResponseEntity<>(groupService.getGroup(id), HttpStatus.OK);
    }

    @GetMapping("/group-users/{id}")
    public ResponseEntity<List<UserModel>> getGroupUsers(@PathVariable(value = "id") Long id) {
        log.info("[REQUEST RECEIVED    -    GET     /group-users/{}]", id);
        return new ResponseEntity<>(groupService.getGroupUsers(id), HttpStatus.OK);
    }

    @PostMapping("/group")
    public ResponseEntity<GroupModel> postGroup(
            @RequestHeader(name = "user", required = false) Long user,
            @RequestHeader(name = "token", required = false) String token,
            @RequestBody GroupModel groupModel) {
        log.info("[REQUEST RECEIVED    -    POST    /group    {}]", groupModel.getGroupName());
        if (loginService.checkIdToken(user, token)) {
            groupService.save(groupModel);
            return new ResponseEntity<>(groupModel, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/group-id-categories/{group}")
    public ResponseEntity<List<Long>> getGroupIdCategories(@PathVariable(value = "group") Long group) {
        log.info("[REQUEST RECEIVED    -    GET     /group-id-categories]");
        return ResponseEntity.ok(groupService.getIdCategories(group));
    }

    @GetMapping("/group/{group}/categories")
    public ResponseEntity<List<CategoryModel>> getGroupCategories(@PathVariable(value = "group") Long group) {
        log.info("[REQUEST RECEIVED    -    GET     /group-categories]");
        return ResponseEntity.ok(groupService.getCategories(group));
    }

    @GetMapping("/group/{user}")
    public ResponseEntity<List<GroupModel>> getGroupsOfUser(@PathVariable(value = "user") Long user) {
        log.info("[REQUEST RECEIVED    -    GET     /group/user {}]", user);
        return ResponseEntity.ok(groupService.getGroupUser(user));
    }
}