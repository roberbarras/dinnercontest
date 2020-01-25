package com.api.dinnercontest;

public enum Errors {

    DUPLICATED("001", "DUPLICATE_ENTRY", "Registro duplicado"),
    NOT_FOUND("002", "NOT_FOUND", "Recurso no encontrado");

    private final String code;
    private final String error;
    private final String message;

    Errors(String code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}