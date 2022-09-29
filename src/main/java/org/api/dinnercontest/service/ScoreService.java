package org.api.dinnercontest.service;

import org.api.dinnercontest.model.AssessmentModel;
import org.api.dinnercontest.model.CategoryModel;
import org.api.dinnercontest.model.ScoreModel;
import org.api.dinnercontest.repository.GroupRepository;
import org.api.dinnercontest.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.frequency;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    private final GroupRepository groupRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository, GroupRepository groupRepository) {
        this.scoreRepository = scoreRepository;
        this.groupRepository = groupRepository;
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
        List<Long> categories = assessmentModel.getAssessment()
                .stream().map(ScoreModel::getCategoryId)
                .collect(Collectors.toList());
        return checkDuplicates(categories)
                && checkBelongGroup(categories, assessmentModel.getRestaurant());
    }

    public void saveAssessment(AssessmentModel assessmentModel) {
        if (checkAssessment(assessmentModel)) {
            scoreRepository.saveAssessment(assessmentModel);
        } else {
            throw new RuntimeException("Error en la valoración");
        }
    }

    private boolean checkBelongGroup(List<Long> categories, Long restaurant) {
        List<Long> group = groupRepository.getIdCategories(groupRepository.getGroupOfRestaurant(restaurant));
        return new HashSet<>(categories).equals(new HashSet<>(group));
    }

    private boolean checkDuplicates(List<Long> categories) {
        return categories.stream()
                .filter(i -> frequency(categories, i) > 1)
                .distinct().count() == 0;
    }
}
