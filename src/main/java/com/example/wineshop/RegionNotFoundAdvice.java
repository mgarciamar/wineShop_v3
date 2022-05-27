package com.example.wineshop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class RegionNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RegionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String RegionNotFoundHandler(RegionNotFoundException ex) {
        return ex.getMessage();
    }


}
