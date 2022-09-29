package org.api.dinnercontest.controller;

import org.api.dinnercontest.model.AssessmentModel;
import org.api.dinnercontest.model.CategoryModel;
import org.api.dinnercontest.service.LoginService;
import org.api.dinnercontest.service.ScoreService;
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
public class ScoreController {

    private ScoreService scoreService;

    private LoginService loginService;

    @Autowired
    public ScoreController(ScoreService scoreService, LoginService loginService) {
        this.scoreService = scoreService;
        this.loginService = loginService;
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryModel> postCategory(
            @RequestHeader(name = "user", required = false) Long user,
            @RequestHeader(name = "token", required = false) String token,
            @RequestBody CategoryModel categoryModel) {
//        log.info("[REQUEST RECEIVED    -    POST    /category    {}]", categoryModel.getCategoryName());
        if (loginService.checkIdToken(user, token)) {
            scoreService.saveCategory(categoryModel, user);
            return new ResponseEntity<>(categoryModel, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity deleteCategory(
            @RequestHeader(name = "user", required = false) Long user,
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable(value = "id") Long id) {
//        log.info("[REQUEST RECEIVED    -    DELETE  /category    {}]", id);
        if (loginService.checkIdToken(user, token)) {
            scoreService.deleteCategory(id, user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryModel> getCategory(@PathVariable(value = "id") Long id) {
//        log.info("[REQUEST RECEIVED    -    GET     /cagetory/{}", id);
        return ResponseEntity.ok(scoreService.getCategory(id));
    }

    @PostMapping("/assessment")
    public ResponseEntity<AssessmentModel> postAssesment(
            @RequestHeader(name = "user", required = false) Long user,
            @RequestHeader(name = "token", required = false) String token,
            @RequestBody AssessmentModel assessmentModel) {
//        log.info("[REQUEST RECEIVED    -    POST    /assessment   by user {}]", assessmentModel.getUser());
        if (loginService.checkIdToken(user, token)) {
            scoreService.saveAssessment(assessmentModel);
            return new ResponseEntity<>(assessmentModel, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}