package com.api.dinnercontest.exception;

public class NotValidException extends RuntimeException {

    public NotValidException() {
        super("El objeto no es válido");
    }
}
