package com.example.restapplicationjpa.survivor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SurvivorNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SurvivorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String survivorNotFoundHandler(SurvivorNotFoundException ex) {
        return ex.getMessage();
    }
}
