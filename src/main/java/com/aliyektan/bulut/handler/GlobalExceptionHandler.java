package com.aliyektan.bulut.handler;

import com.aliyektan.bulut.dto.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

/**
 * GlobalExceptionHandler catches expected or unexpected exceptions and control them all
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeExpcetions(Exception ex, WebRequest request) {

        Response response = Response
                .builder()
                .errorDescription(ex.getMessage())
                .httpStatus(HttpStatus.CONFLICT.value())
                .build();

        ex.printStackTrace();
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {

        Response response = Response
                .builder()
                .errorDescription(ex.getMessage())
                .httpStatus(HttpStatus.CONFLICT.value())
                .build();

        ex.printStackTrace();

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<Object> handleNullPointerExceptions(Exception ex, WebRequest request) {

        Response response = Response
                .builder()
                .errorDescription(ex.getMessage() == null ? "null reference" : ex.getMessage())
                .httpStatus(HttpStatus.CONFLICT.value())
                .build();

        ex.printStackTrace();

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleDataIntegrityViolationExceptions(Exception ex, WebRequest request) {

        Response response = Response
                .builder()
                .errorDescription(ex.getMessage())
                .httpStatus(HttpStatus.CONFLICT.value())
                .build();

        ex.printStackTrace();

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolationExceptions(Exception ex, WebRequest request) {

        Response response = Response
                .builder()
                .errorDescription(((ConstraintViolationException) ex).getConstraintViolations().iterator().next().getMessage())
                .httpStatus(HttpStatus.CONFLICT.value())
                .build();

        ex.printStackTrace();

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundExceptions(Exception ex, WebRequest request) {

        Response response = Response
                .builder()
                .errorDescription(ex.getMessage())
                .httpStatus(HttpStatus.CONFLICT.value())
                .build();

        ex.printStackTrace();

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
