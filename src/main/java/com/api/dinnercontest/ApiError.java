package com.api.dinnercontest;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public class ApiError {

    private String timestamp;
    private HttpStatus status;
    private String error;
    private String message;
    private String path;

    //TODO implementar patrón builder y eliminar constructor
    public ApiError(String timestamp, HttpStatus status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    //TODO implementar patrón builder y eliminar constructor
    public ApiError(HttpStatus status, String error, String message, String path) {
        this.timestamp = Instant.now().toString();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    //TODO implementar patrón builder y eliminar constructor
    public ApiError(HttpStatus status, String error, String message) {
        this.timestamp = Instant.now().toString();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ApiError() {
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
