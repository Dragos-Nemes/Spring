package com.example.restapplicationjpa.survivor;

public class SurvivorNotFoundException extends RuntimeException {

    SurvivorNotFoundException(Long id) {
        super("Could not find Survivor" + id);
    }
}
