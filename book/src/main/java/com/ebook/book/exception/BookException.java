package com.ebook.book.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class BookException extends RuntimeException{
    Map<String, Object> validation;

    public BookException(String message) {
        super(message);

    }

    public BookException(String message, Map<String, Object> validation) {
        super(message);
        this.validation = validation;
    }
}
