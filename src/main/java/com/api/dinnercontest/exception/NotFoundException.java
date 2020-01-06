package com.api.dinnercontest.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("El recurso solicitado no ha sido encontrado");
    }
}
