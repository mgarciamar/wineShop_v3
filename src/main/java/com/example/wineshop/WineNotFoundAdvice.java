package com.example.wineshop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WineNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(WineNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String WineNotFoundHandler(WineNotFoundException ex) {
        return ex.getMessage();
    }
}
