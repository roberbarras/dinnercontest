package com.api.dinnercontest.model;

public class UserTokenModel {

    private String accessName;

    private String token;

    public UserTokenModel() {
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
