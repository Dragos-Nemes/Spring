package com.example.restapplicationjpa.survivor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SurvivorService {
    private final SurvivorRepository repository;
    public SurvivorService(SurvivorRepository repository) {
        this.repository = repository;
    }

    public SurvivorDTO getSurvivorById(long id) {
        Survivor survivor = repository.findById(id)
                .orElseThrow(() -> new SurvivorNotFoundException(id));
        SurvivorDTO survivorDTO = new SurvivorDTO();
        survivorDTO.setLastName(survivor.getLastName());
        survivorDTO.setFirstName(survivor.getFirstName());
        survivorDTO.setId(survivor.getId());
        return survivorDTO;
    }

    public SurvivorDTO saveSurvivor(SurvivorDTO survivorDTO) {
        Survivor survivor = new Survivor();
        return saveDtoToDb(survivorDTO, survivor);
    }

    public List<SurvivorDTO> getAllSurvivors() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(survivor -> {
                    SurvivorDTO survivorDTO = new SurvivorDTO();
                    survivorDTO.setId(survivor.getId());
                    survivorDTO.setLastName(survivor.getLastName());
                    survivorDTO.setFirstName(survivor.getFirstName());
                    return survivorDTO;
                }).collect(Collectors.toList());
    }

    public SurvivorDTO update(@RequestBody SurvivorDTO newSurvivor, @PathVariable long id) {
        return repository.findById(id)
                .map(survivor -> saveDtoToDb(newSurvivor, survivor))
                .orElseGet(() -> {
                    Survivor survivor = new Survivor();
                    return saveDtoToDb(newSurvivor, survivor);
                });
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    private SurvivorDTO saveDtoToDb(SurvivorDTO newSurvivor, Survivor survivor) {
        survivor.setId(newSurvivor.getId());
        survivor.setLastName(newSurvivor.getLastName());
        survivor.setFirstName(newSurvivor.getFirstName());
        repository.save(survivor);
        return newSurvivor;
    }
}

