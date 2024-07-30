package com.foro.foro_alura.exception.advice;

import com.foro.foro_alura.exception.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExcepcionHandler {
    private Map<String, Object> errorMap = new HashMap<>();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return errors;
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> userNotFound(UserNotFoundException exception){
        errorMap.put("Status", "Error");
        errorMap.put("Message", exception.getMessage());
        errorMap.put("Code", HttpStatus.NOT_FOUND);

        return new ResponseEntity(errorMap, HttpStatus.NOT_FOUND);
    }
}
