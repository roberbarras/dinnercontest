package com.api.dinnercontest;

import org.springframework.http.HttpStatus;

public class ApiError {

    private String timestamp;
    private HttpStatus status;
    private String message;
    private String path;

    public ApiError() {
    }

    public ApiError(HttpStatus httpStatus, String message) {
        status = httpStatus;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
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
}
