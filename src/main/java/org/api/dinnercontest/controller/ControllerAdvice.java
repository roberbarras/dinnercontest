package org.api.dinnercontest.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.api.dinnercontest.ApiError;
import org.api.dinnercontest.Errors;
import org.springframework.dao.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity exception(NoSuchElementException exception, HttpServletRequest request) {
//        log.error("path: {}", request.getServletPath());
//        log.error(exception.getLocalizedMessage());
        Errors error = Errors.get(exception.getMessage());
        ApiError apiError = new ApiError(error.getCode(), error.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity exception(EmptyResultDataAccessException exception, HttpServletRequest request) {
//        log.error("path: {}", request.getServletPath());
//        log.error(exception.getLocalizedMessage());
        Errors error = Errors.NOT_FOUND;
        ApiError apiError = new ApiError(error.getCode(), error.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    public ResponseEntity exception(IndexOutOfBoundsException exception, HttpServletRequest request) {
//        log.error("path: {}", request.getServletPath());
//        log.error("No existe el elemento solicitado " + exception.getLocalizedMessage());
        Errors error = Errors.NOT_FOUND;
        ApiError apiError = new ApiError(error.getCode(), error.getMessage(), request.getServletPath());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvocationTargetException.class)
    public ResponseEntity exception(InvocationTargetException exception, HttpServletRequest request) {
//        log.error("path: {}", request.getServletPath());
//        log.error(exception.getLocalizedMessage());
        Errors error = Errors.get(exception.getMessage());
        ApiError apiError = new ApiError(error.getCode(), error.getMessage(), request.getServletPath());
        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity exception(HttpMessageNotReadableException exception, HttpServletRequest request) {
//        log.error("path: {}", request.getServletPath());
//        log.error(exception.getLocalizedMessage());
        Errors error = Errors.get(exception.getMessage());
        ApiError apiError = new ApiError(error.getCode(), error.getMessage(), request.getServletPath());
        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataAccessResourceFailureException.class)
    public ResponseEntity exception(DataAccessResourceFailureException exception, HttpServletRequest request) {
//        log.error("path: {}", request.getServletPath());
//        log.error(exception.getLocalizedMessage());
        Errors error = Errors.get(exception.getMessage());
        ApiError apiError = new ApiError(error.getCode(), error.getMessage(), request.getServletPath());
        return new ResponseEntity(apiError, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResponseEntity exception(DuplicateKeyException exception, HttpServletRequest request) {
//        log.error("path: {}", request.getServletPath());
//        log.error(exception.getCause().getLocalizedMessage());
        Errors error = Errors.get(exception.getMessage());
        ApiError apiError = new ApiError(error.getCode(), error.getMessage(), request.getServletPath());
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity exception(DataIntegrityViolationException exception, HttpServletRequest request) {
//        log.error("path: {}", request.getServletPath());
        Errors error = Errors.get(exception.getMessage());
        ApiError apiError = new ApiError(error.getCode(), error.getMessage(), request.getServletPath());
        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class)
    public ResponseEntity exception(InvalidDataAccessResourceUsageException exception, HttpServletRequest request) {
//        log.error("path: {}", request.getServletPath());
//        log.error(exception.getLocalizedMessage());
        Errors error = Errors.get(exception.getMessage());
        ApiError apiError = new ApiError(error.getCode(), error.getMessage(), request.getServletPath());
        return new ResponseEntity(apiError, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception exception, HttpServletRequest request) {
//        log.error("path: {}", request.getServletPath());
//        log.error(exception.getLocalizedMessage());
        Errors error = Errors.get(exception.getMessage());
        ApiError apiError = new ApiError(error.getCode(), error.getMessage(), request.getServletPath());
        return new ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
