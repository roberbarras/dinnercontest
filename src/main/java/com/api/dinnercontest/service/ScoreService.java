package com.api.dinnercontest.service;

import com.api.dinnercontest.model.CategoryModel;
import com.api.dinnercontest.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public void saveCategory(CategoryModel categoryModel, Long user) {
        scoreRepository.saveCategory(categoryModel, user);
    }

    public CategoryModel getCategory(Long id) {
        return scoreRepository.getCategory(id);
    }

    public void deleteCategory(Long id, Long user) {
        scoreRepository.deleteCategory(id, user);
    }
}
