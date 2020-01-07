package com.api.dinnercontest.controller;

import com.api.dinnercontest.model.GroupModel;
import com.api.dinnercontest.service.GroupService;
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
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/group/{id}")
    public ResponseEntity getGroup(HttpServletRequest request, @PathVariable(value = "id") Long id) {
        //log.info("[REQUEST RECEIVED    -    GET    /group/{}]", id);
        return new ResponseEntity<>(groupService.getGroup(id), HttpStatus.OK);
    }

    @GetMapping("/group-users/{id}")
    public ResponseEntity getGroupUsers(HttpServletRequest request, @PathVariable(value = "id") Long id) {
        //log.info("[REQUEST RECEIVED    -    GET    /group-users/{}]", id);
        return new ResponseEntity<>(groupService.getGroupUsers(id), HttpStatus.OK);
    }

    @PostMapping("/group")
    public ResponseEntity postUser(HttpServletRequest request, @RequestBody GroupModel groupModel) {
        //log.info("[REQUEST RECEIVED    -    POST    /group    {}]", groupModel.getName());
        groupService.save(groupModel);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri());
        return new ResponseEntity<>(groupModel, httpHeaders, HttpStatus.CREATED);
    }
}