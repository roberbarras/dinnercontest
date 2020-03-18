package com.api.dinnercontest.model;

import java.time.LocalDateTime;

public class CategoryModel extends UserIdTokenModel {

    private Long categoryId;

    private Long groupId;

    private String categoryName;

    private int weighing;

    private LocalDateTime creationDate;

    private LocalDateTime removalDate;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getRemovalDate() {
        return removalDate;
    }

    public void setRemovalDate(LocalDateTime removalDate) {
        this.removalDate = removalDate;
    }

    public CategoryModel() {
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "categoryId=" + categoryId +
                ", groupId=" + groupId +
                ", categoryName='" + categoryName + '\'' +
                ", weighing=" + weighing +
                '}';
    }
}
