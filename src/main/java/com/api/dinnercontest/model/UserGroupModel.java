package com.api.dinnercontest.model;

public class UserGroupModel extends UserIdTokenModel {

    private Long groupId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "UserGroupModel { " +
                " userId: " + super.getUserId() +
                ", groupId: " + groupId +
                " }";
    }
}
