package com.alura.foro.infra;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    /* Handler the error of entitynotfound to an 404*/
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(Exception exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException e) {
        var error = e.getFieldErrors().stream().map(Error::new).toList();
        return ResponseEntity.badRequest().body(error);
    }

    /*To be able to understand the information of the error*/
    private record Error(String field, String message) {
        public Error(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
