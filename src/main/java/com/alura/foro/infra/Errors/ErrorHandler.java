package com.alura.foro.infra.Errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The ErrorHandler class is responsible for handling and providing responses for various types of errors
 * that may occur in the application.
 */
@RestControllerAdvice
public class ErrorHandler {

    /**
     * Handles the error of EntityNotFoundException by responding with a 404 status.
     *
     * @param exception The EntityNotFoundException instance.
     * @return A ResponseEntity with a 404 status.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(Exception exception) {
        return ResponseEntity.notFound().build();
    }

    /**
     * Handles the error of MethodArgumentNotValidException by responding with a 400 status
     * and providing details about validation errors.
     *
     * @param e The MethodArgumentNotValidException instance.
     * @return A ResponseEntity with a 400 status and details about validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException e) {
        var error = e.getFieldErrors().stream().map(Error::new).toList();
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Represents an error with details such as the field and message.
     */
    private record Error(String field, String message) {
        /**
         * Constructs an Error instance based on a FieldError.
         *
         * @param fieldError The FieldError instance.
         */
        public Error(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
