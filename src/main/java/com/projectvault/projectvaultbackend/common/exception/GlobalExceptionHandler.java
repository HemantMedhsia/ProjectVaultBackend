package com.projectvault.projectvaultbackend.common.exception;

import com.projectvault.projectvaultbackend.common.response.ApiResponse;
import com.projectvault.projectvaultbackend.common.response.ErrorStructureEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        for (FieldError e : ex.getBindingResult().getFieldErrors()) {
            errors.put(e.getField(), e.getDefaultMessage());
        }
        body.put("message", "Validation failed");
        body.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    //    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
//    public ResponseEntity<ErrorStructureEntity> handleAuth(RuntimeException ex) {
//        return ApiResponse.error(
//                ex.getMessage(),
//                "Not Authenticated",
//                HttpStatus.UNAUTHORIZED
//        );
//    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorStructureEntity> handleNotFound(ResourceNotFoundException ex) {
        return ApiResponse.error(
                ex.getMessage(),
                "Resource Not Found",
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorStructureEntity> handleDup(DataIntegrityViolationException ex) {
        return ApiResponse.error(
                ex.getMostSpecificCause().getMessage(),
                "Duplicate or conflicting data",
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorStructureEntity> handleGeneric(Exception ex) {
        return ApiResponse.error(
                ex.getMessage(),
                "Internal error",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
