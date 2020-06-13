package com.api.dinnercontest;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {

    private String timestamp;
    private String code;
    private String message;
    private String path;

    public ApiError() {
    }

    public ApiError(String code, String message, String path) {
        this.timestamp = LocalDateTime.now().toString();
        this.code = code;
        this.message = message;
        this.path = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
