package com.api.dinnercontest.model;

public class GroupCategoryModel extends UserIdTokenModel {

    private long groupId;

    private long categoryId;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public GroupCategoryModel() {
    }
}
