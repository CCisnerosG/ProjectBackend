package com.example.ProjectBackend.repositories;

import com.example.ProjectBackend.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    List<Province> findByCountryId(Integer countryId);
}
