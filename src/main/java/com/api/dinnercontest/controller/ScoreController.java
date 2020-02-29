package com.api.dinnercontest.controller;

import com.api.dinnercontest.model.CategoryModel;
import com.api.dinnercontest.service.ScoreService;
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

    private final ScoreService scoreService;

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryModel> postCategory(@RequestBody CategoryModel categoryModel) {
        log.info("[REQUEST RECEIVED    -    POST    /category    {}]", categoryModel.getCategoryName());
        scoreService.saveCategory(categoryModel);
        return new ResponseEntity<>(categoryModel, HttpStatus.CREATED);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryModel> getCategory(@PathVariable(value = "id") Long id) {
        log.info("[REQUEST RECEIVED    -    GET    /cagetory/{}", id);
        return ResponseEntity.ok(scoreService.getCategory(id));
    }
}
