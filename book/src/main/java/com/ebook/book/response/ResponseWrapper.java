package com.ebook.book.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
public class ResponseWrapper<T>{

    private Integer statusCode = HttpStatus.OK.value();

    private String message = "Successful";

    private T data;

    private Map<String, Object> validation;

    @JsonIgnore
    private HttpStatus httpStatus = HttpStatus.OK;

    public ResponseWrapper() {}

    /**
     * For the default response message passing only th body
     * @param body
     */
    public ResponseWrapper(T body) {
        setSuccessParams(body);
    }

    /**
     * For response with the body and custom messages
     * @param body
     * @param message
     */
    public ResponseWrapper(T body, String message) {
        setSuccessParams(body, message);
    }

    /**
     * For the default response message passing body and status
     * @param body
     */
    public ResponseWrapper(T body, HttpStatus status) {
        this.statusCode = status.value();
        setSuccessParams(body);
    }

    /**
     * For response with body, custom message and custom status
     * @param body
     * @param message
     * @param status
     */
    public ResponseWrapper(T body, String message, HttpStatus status) {
        this.statusCode = status.value();
        setSuccessParams(body, message);
    }

    private void setSuccessParams(T body, String message){
        setMessage(message);
        setData(body);
        setValidation(null);
    }

    private void setSuccessParams(T body){
        setSuccessParams(body, message);
    }
}
