package com.example.restapplicationjpa.survivor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SurvivorController {

    private final SurvivorRepository repository;

    SurvivorController(SurvivorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/survivors")
    List<Survivor> all() {
        return repository.findAll();
    }

    @PostMapping("/survivors")
    Survivor newSurvivor(@RequestBody Survivor newSurvivor) {
        return repository.save(newSurvivor);
    }

    @GetMapping("/survivors/{id}")
    Survivor one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new SurvivorNotFoundException(id));
    }

    @PutMapping("/survivors/{id}")
    Survivor replaceSurvivor(@RequestBody Survivor newSurvivor, @PathVariable Long id) {

        return repository.findById(id)
                .map(survivor -> {
                    survivor.setFirstName(newSurvivor.getFirstName());
                    survivor.setLastName(newSurvivor.getLastName());
                    return repository.save(survivor);
                })
                .orElseGet(() -> {
                    newSurvivor.setId(id);
                    return repository.save(newSurvivor);
                });
    }

    @DeleteMapping("/survivors/{id}")
    void deleteSurvivor(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
