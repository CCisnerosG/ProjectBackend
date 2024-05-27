package com.example.ProjectBackend.repositories;

import com.example.ProjectBackend.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
