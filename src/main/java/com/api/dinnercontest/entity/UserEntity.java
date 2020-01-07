package com.api.dinnercontest.entity;

import java.time.LocalDateTime;

public class UserEntity {

    private Long userId;

    private String userName;

    private String accessName;

    private LocalDateTime creationDate;

    private LocalDateTime lastLogin;

    private String email;

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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", accessName='" + accessName + '\'' +
                ", creationDate=" + creationDate +
                ", lastLogin=" + lastLogin +
                ", email='" + email + '\'' +
                '}';
    }
}
