package com.api.dinnercontest.exception;

public class RowAlreadyExistException extends RuntimeException {
    public RowAlreadyExistException() {
        super("El registro ya existe en la base de datos");
    }
}
