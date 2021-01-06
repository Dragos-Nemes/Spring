package com.example.restapplicationjpa.survivor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SurvivorRepository extends JpaRepository<Survivor, Long> {
}
//import org.springframework.data.repository.CrudRepository;
//
//public interface SurvivorRepository extends CrudRepository<Survivor, Long> {
//}