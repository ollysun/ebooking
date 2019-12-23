package com.ebook.book.response;

import com.ebook.book.exception.BookException;
import com.ebook.book.exception.ErrorValidation;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {


    /**
     * This method handles exceptions thrown involving MethodArgumentNotValidException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(errorResponseBuilder(HttpStatus.BAD_REQUEST, "Parameters not valid", ErrorValidation.getErrors(e)), HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles exceptions thrown involving IllegalArgumentException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(IllegalArgumentException e) {
        return new ResponseEntity<>(errorResponseBuilder(HttpStatus.BAD_REQUEST, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }


    /**
     * This method handles exceptions thrown involving NotFoundException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BookException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(BookException e) {
        return new ResponseEntity<>(errorResponseBuilder(HttpStatus.NOT_FOUND, e.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles exceptions thrown involving InvalidFormatException
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(InvalidFormatException e){
        return new ResponseEntity<>(errorResponseBuilder(HttpStatus.BAD_REQUEST, "Invalid data type format spotted in request",null), HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles exceptions thrown involving HttpMessageNotReadableException
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(HttpMessageNotReadableException e){
        return new ResponseEntity<>(errorResponseBuilder(HttpStatus.BAD_REQUEST, "Bad request",null), HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles exceptions thrown involving DuplicateKeyException
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(DuplicateKeyException e){
        return new ResponseEntity<>(errorResponseBuilder(HttpStatus.CONFLICT, "Similar record already exist",null), HttpStatus.CONFLICT);
    }

    /**
     * This method handles exceptions thrown involving HttpRequestMethodNotSupportedException
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(HttpRequestMethodNotSupportedException e){
        return new ResponseEntity<>(errorResponseBuilder(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage(),null), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * This method handles exceptions thrown involving MissingServletRequestParameterException
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(MissingServletRequestParameterException e){
        return new ResponseEntity<>(errorResponseBuilder(HttpStatus.BAD_REQUEST, e.getMessage(),null), HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles exceptions thrown involving Exception
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(Exception e){
        return new ResponseEntity<>(errorResponseBuilder(HttpStatus.BAD_REQUEST, "Something went wrong while trying to process your request",null), HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles exceptions thrown involving Exception
     * @param e
     * @return
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleException(DataAccessException e){
        return new ResponseEntity<>(errorResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "Data Access Exception error",null), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * This method builds the error response
     *
     * @param statusCode
     * @param message
     * @param validation
     * @return
     */
    private static ResponseWrapper<Object> errorResponseBuilder(HttpStatus statusCode, String message, Map<String, Object> validation) {
        ResponseWrapper<Object> response = new ResponseWrapper<>();
        response.setStatusCode(statusCode.value());
        response.setMessage(message);
        response.setValidation(validation);
        return response;
    }
}
