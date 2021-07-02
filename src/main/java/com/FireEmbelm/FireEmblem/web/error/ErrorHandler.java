package com.FireEmbelm.FireEmblem.web.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private HttpServletRequest mHttpServletRequest;

    private ResponseEntity<Object> convertToResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.httpStatus);
    }

}
