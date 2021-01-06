package com.example.restapplicationjpa.survivor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SurvivorNotFoundException extends RuntimeException {

    SurvivorNotFoundException(Long id) {
        super("Could not find Survivor " + id);
    }
}
