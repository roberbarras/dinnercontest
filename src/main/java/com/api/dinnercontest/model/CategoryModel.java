package com.api.dinnercontest.model;

public class CategoryModel extends UserIdTokenModel {

    private Long categoryId;

    private String categoryName;

    private int weighing;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getWeighing() {
        return weighing;
    }

    public void setWeighing(int weighing) {
        this.weighing = weighing;
    }

    public CategoryModel() {
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", weighing='" + weighing + '\'' +
                '}';
    }
}
