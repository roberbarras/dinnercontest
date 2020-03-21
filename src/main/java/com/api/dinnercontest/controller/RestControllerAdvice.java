package com.api.dinnercontest.controller;

import com.api.dinnercontest.ApiError;
import com.api.dinnercontest.exception.NotFoundException;
import com.api.dinnercontest.exception.NotValidException;
import com.api.dinnercontest.exception.RowAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

@Slf4j
@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> exception(NotFoundException exception) {
        log.error(exception.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), exception.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> exception(NoSuchElementException exception) {
        log.error(exception.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), exception.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    public ResponseEntity<Object> exception(IndexOutOfBoundsException exception) {
        log.error("No existe el elemento solicitado " + exception.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No existe el elemento solicitado ", exception.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = NotValidException.class)
    public ResponseEntity<Object> exception(NotValidException exception) {
        log.error(exception.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), exception.getMessage());
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = RowAlreadyExistException.class)
    public ResponseEntity<Object> exception(RowAlreadyExistException exception) {
        log.error(exception.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), exception.getMessage());
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = InvocationTargetException.class)
    public ResponseEntity<Object> exception(InvocationTargetException exception) {
        log.error(exception.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), exception.getMessage());
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> exception(HttpMessageNotReadableException exception) {
        log.error(exception.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), exception.getMessage());
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = DataAccessResourceFailureException.class)
    public ResponseEntity<Object> exception(DataAccessResourceFailureException exception) {
        log.error(exception.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.BAD_GATEWAY, exception.getLocalizedMessage(), exception.getMessage());
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResponseEntity<Object> exception(DuplicateKeyException exception) {
        log.error(exception.getCause().getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), exception.getMessage());
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> exception(DataIntegrityViolationException exception) {
        log.error(exception.getCause().getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getCause().getLocalizedMessage(), exception.getMessage());
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<Object> exception(InvalidDataAccessResourceUsageException exception) {
        log.error(exception.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.BAD_GATEWAY, exception.getLocalizedMessage(), exception.getCause().getMessage());
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        log.error(exception.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage(), exception.getMessage());
        return new ResponseEntity(apiError, apiError.getStatus());
    }
}
