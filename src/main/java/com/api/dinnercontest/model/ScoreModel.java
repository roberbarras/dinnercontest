package com.api.dinnercontest.model;

public class ScoreModel {

    private Long categoryId;
    private int value;
    private Long assessmentId;

    public ScoreModel() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
                "category=" + categoryId +
                ", score=" + value +
                '}';
    }
}
