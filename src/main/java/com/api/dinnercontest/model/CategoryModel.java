package com.api.dinnercontest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.time.LocalDateTime;

public class CategoryModel {

    private Long categoryId;

    @JsonProperty(access = Access.WRITE_ONLY)
    private Long groupId;

    private String categoryName;

    private int weighing;

    @JsonIgnore
    private LocalDateTime creationDate;

    @JsonIgnore
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
