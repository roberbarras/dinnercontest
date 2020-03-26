package com.api.dinnercontest.model;

public class ScoreModel {

    private Long category;
    private int value;
    private Long assessmentId;

    public ScoreModel() {
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    @Override
    public String toString() {
        return "ScoreModel{" +
                "category=" + category +
                ", score=" + value +
                '}';
    }
}
