package com.api.dinnercontest.model;

public class UserGroupModel {

    private Long userId;

    private Long groupId;

    private String token;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserGroupModel { " +
                " userId: " + userId +
                ", groupId: " + groupId +
                " }";
    }
}
