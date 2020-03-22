package com.api.dinnercontest.model;

public class ScoreModel {

    private Long category;
    private int score;

    public ScoreModel() {
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScoreModel{" +
                "category=" + category +
                ", score=" + score +
                '}';
    }
}
