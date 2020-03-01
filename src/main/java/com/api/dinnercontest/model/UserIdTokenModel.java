package com.api.dinnercontest.model;

public class UserIdTokenModel {

    private Long userId;

    private String token;

    public UserIdTokenModel() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
