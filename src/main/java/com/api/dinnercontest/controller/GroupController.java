package com.api.dinnercontest.controller;

import com.api.dinnercontest.model.CategoryModel;
import com.api.dinnercontest.model.GroupCategoryModel;
import com.api.dinnercontest.model.GroupModel;
import com.api.dinnercontest.model.UserModel;
import com.api.dinnercontest.service.GroupService;
import com.api.dinnercontest.service.NotificationService;
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
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class GroupController {

    private GroupService groupService;

    private NotificationService notificationService;

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<GroupModel> getGroup(HttpServletRequest request, @PathVariable(value = "id") Long id) {
        log.info("[REQUEST RECEIVED    -    GET    /group/{}]", id);
        return new ResponseEntity<>(groupService.getGroup(id), HttpStatus.OK);
    }

    @GetMapping("/group-users/{id}")
    public ResponseEntity<List<UserModel>> getGroupUsers(HttpServletRequest request, @PathVariable(value = "id") Long id) {
        log.info("[REQUEST RECEIVED    -    GET    /group-users/{}]", id);
        return new ResponseEntity<>(groupService.getGroupUsers(id), HttpStatus.OK);
    }

    @PostMapping("/group")
    public ResponseEntity<GroupModel> postGroup(HttpServletRequest request, @RequestBody GroupModel groupModel) {
        log.info("[REQUEST RECEIVED    -    POST    /group    {}]", groupModel.getGroupName());
        groupService.save(groupModel);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri());
        return new ResponseEntity<>(groupModel, httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/group-categories")
    public ResponseEntity<GroupCategoryModel> postGroupCategories(@RequestBody GroupCategoryModel groupCategoryModel) {
        log.info("[REQUEST RECEIVED    -    POST    /group-categories]");
        groupService.saveCategory(groupCategoryModel);
        return new ResponseEntity<>(groupCategoryModel, HttpStatus.CREATED);
    }

    @GetMapping("/group-id-categories/{group}")
    public ResponseEntity<List<Long>> getGroupIdCategories(@PathVariable(value = "group") Long group) {
        log.info("[REQUEST RECEIVED    -    GET    /group-id-categories]");
        return ResponseEntity.ok(groupService.getIdCategories(group));
    }

    @GetMapping("/group-categories/{group}")
    public ResponseEntity<List<CategoryModel>> getGroupCategories(@PathVariable(value = "group") Long group) {
        log.info("[REQUEST RECEIVED    -    GET    /group-categories]");
        return ResponseEntity.ok(groupService.getCategories(group));
    }
}