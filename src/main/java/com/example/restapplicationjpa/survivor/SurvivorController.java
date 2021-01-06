package com.example.restapplicationjpa.survivor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SurvivorController {


    private final SurvivorService survivorService;
    @GetMapping("/survivors")
    List<SurvivorDTO> all() {
        return survivorService.getAllSurvivors();
    }

    @PostMapping("/survivors")
    SurvivorDTO newSurvivor(@RequestBody SurvivorDTO newSurvivor) {
        return survivorService.saveSurvivor(newSurvivor);
    }

    @GetMapping("/survivors/{id}")
    SurvivorDTO one(@PathVariable Long id) {

        return survivorService.getSurvivorById(id);
    }

    @PutMapping("/survivors/{id}")
    SurvivorDTO replaceSurvivor(@RequestBody SurvivorDTO newSurvivor, @PathVariable Long id) {

        return survivorService.update(newSurvivor, id);
    }

    @DeleteMapping("/survivors/{id}")
    void deleteSurvivor(@PathVariable Long id) {
        survivorService.delete(id);
    }
}
