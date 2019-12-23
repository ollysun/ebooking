package com.ebook.book.exception;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

/**
 * This class handles error validation
 */
public class ErrorValidation {
    private ErrorValidation() {
    }

    public static Map<String, Object> getErrors (MethodArgumentNotValidException methodArgumentNotValid){
            Map <String, Object> errors = new HashMap<>();
            for(FieldError fieldError : methodArgumentNotValid.getBindingResult().getFieldErrors()) {
                String fieldName = fieldError.getField();
                if(fieldError.getField().contains("data.")){
                    fieldName = fieldName.replace("data.","");
                }
                errors.put(fieldName, fieldError.getDefaultMessage());
            }
            return errors.keySet().isEmpty() ? null : errors;
        }
}
