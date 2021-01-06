package com.example.restapplicationjpa.survivor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(SurvivorRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Survivor("Dwight", "Fairfield")));
            log.info("Preloading " + repository.save(new Survivor("Meg", "Thomas")));
            log.info("Preloading " + repository.save(new Survivor("Blendette", "Morel")));
            log.info("Preloading " + repository.save(new Survivor("Jake", "Park")));
            log.info("Preloading " + repository.save(new Survivor("Nea aka the Entity ", "Karlsson")));
            log.info("Preloading " + repository.save(new Survivor("David", "King")));
            log.info("Preloading " + repository.save(new Survivor("Ace", "Versace")));

        };
    }
}