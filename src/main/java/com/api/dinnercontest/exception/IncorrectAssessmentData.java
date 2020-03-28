package com.api.dinnercontest.exception;

public class IncorrectAssessmentData extends RuntimeException {
    public IncorrectAssessmentData() {
        super("Los datos de la valoración no son correctos");
    }
}
