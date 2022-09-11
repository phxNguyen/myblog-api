package com.springboot.blog.exception;


import com.springboot.blog.payload.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    // handle specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFoundException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetails> handleBlogApiException(ResourceNotFoundException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Cach 1: method lay trong docs ResponseEntityExceptionHandler
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


        // tao ra Map Object de chua <fieldName, validationMessage>
        // ap dung cho all fields trong DTO
        Map<String, String> errors = new HashMap<>();

        // lay tat error tu exception object (MethodArgumentNotValidException ex)
        // getAllErrors(): Get all errors, both global and field ones.
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName =((FieldError)error).getField();
            String validationMessage = error.getDefaultMessage();
            errors.put(fieldName,validationMessage);
        });

        return  new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        //return handleExceptionInternal(ex, null, headers, status, request);
    }

    // Cach 2: giong  custom exception ben tren
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest webRequest){
//
//        Map<String, String> errors = new HashMap<>();
//        exception.getBindingResult().getAllErrors().forEach((error)->{
//            String fieldName =((FieldError)error).getField();
//            String validationMessage = error.getDefaultMessage();
//            errors.put(fieldName,validationMessage);
//        });
//
//        return  new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
}
