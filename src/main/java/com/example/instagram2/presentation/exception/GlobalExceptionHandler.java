package com.example.instagram2.presentation.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.media.Schema;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Schema(description = "Resposta de erro padronizada")
    public static class ErrorResponse {
        @Schema(description = "Mensagem de erro", example = "Title is required")
        private String message;
        
        @Schema(description = "Timestamp do erro", example = "2026-03-16T21:35:00")
        private String timestamp;
        
        @Schema(description = "Campos com erro de validação")
        private Map<String, String> errors;

        public ErrorResponse(String message) {
            this.message = message;
            this.timestamp = java.time.LocalDateTime.now().toString();
            this.errors = new HashMap<>();
        }

        public ErrorResponse(String message, Map<String, String> errors) {
            this.message = message;
            this.timestamp = java.time.LocalDateTime.now().toString();
            this.errors = errors;
        }

        // Getters
        public String getMessage() { return message; }
        public String getTimestamp() { return timestamp; }
        public Map<String, String> getErrors() { return errors; }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        
        ErrorResponse response = new ErrorResponse("Validation failed", errors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse response = new ErrorResponse("Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
