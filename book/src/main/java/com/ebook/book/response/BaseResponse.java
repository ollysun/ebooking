package com.ebook.book.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * This is the base response class that defines the format of responses in this application
 * @param <T>
 */
public class BaseResponse<T> extends ResponseEntity<ResponseWrapper<T>> {

    public BaseResponse(HttpStatus status) {
        super(status);
    }

    /**
     *  This is the default successful response structure
     * @param body
     */
    public BaseResponse(T body) {
        super(new ResponseWrapper<>(body), HttpStatus.OK);
    }

    /**
     * This is for responses with a custom message
     * @param body
     * @param message
     */
    public BaseResponse(T body, String message) {
        super(new ResponseWrapper<>(body, message), HttpStatus.OK);
    }



    public BaseResponse(T body, HttpStatus status) {
        super(new ResponseWrapper<>(body,status), status);
    }

    /**
     * This is for responses where you want to specify a custom message and http status
     * @param body
     * @param message
     */
    public BaseResponse(T body, String message, HttpStatus status) {
        super(new ResponseWrapper<>(body, message, status), status);
    }

    public BaseResponse(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public BaseResponse(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(new ResponseWrapper<>(body), headers, status);
    }
}
