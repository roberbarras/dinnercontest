package com.api.dinnercontest.entity;

public class UserEntity {

    private Long userId;

    private String userName;

    private String accessName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", accessName='" + accessName + '\'' +
                '}';
    }
}
