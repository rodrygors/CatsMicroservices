package com.example.backendshelter.repository;

import com.example.backendshelter.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Optional<Shelter> findByNameContaining(String name);
}
