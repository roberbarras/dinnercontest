package com.api.dinnercontest.model;

import java.util.List;

public class AssessmentModel {

    private Long user;
    private Long restaurant;
    private List<ScoreModel> assessment;

    public AssessmentModel() {
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Long restaurant) {
        this.restaurant = restaurant;
    }

    public List<ScoreModel> getAssessment() {
        return assessment;
    }

    public void setAssessment(List<ScoreModel> assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "user=" + user +
                ", restaurant=" + restaurant +
                ", assessment=" + assessment +
                '}';
    }
}
