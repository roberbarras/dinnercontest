package com.api.dinnercontest.service;

import com.api.dinnercontest.model.AssessmentModel;
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

    public boolean checkAssessment(AssessmentModel assessmentModel) {
        //TODO comprobar categorias ok (no repetidas, todas corresponden a ese grupo, no falta ninguna)
        //TODO comprobar que el usuario es miembro del grupo
        return true;
    }

    public void saveAssessment(AssessmentModel assessmentModel) {
        scoreRepository.saveAssessment(assessmentModel);
    }
}
