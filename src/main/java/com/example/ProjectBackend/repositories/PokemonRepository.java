package com.example.ProjectBackend.repositories;

import com.example.ProjectBackend.entities.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer>, JpaSpecificationExecutor<Pokemon>, PagingAndSortingRepository<Pokemon, Integer> {
    Optional<Pokemon> findById(Integer id);

    List<Pokemon> findByNameContaining(String name);

    List<Pokemon> findByType(String type);

    List<Pokemon> findByGeneration(Integer generation);

    List<Pokemon> findByNameContainingAndType(String name, String type);

    List<Pokemon> findByNameContainingAndTypeAndGeneration(String name, String type, Integer generation);

    List<Pokemon> findByNameContainingAndTypeAndGenerationAndPriceBetween(String name, String type, Integer generation, Integer minPrice, Integer maxPrice);

    List<Pokemon> findByTypeAndGenerationAndPriceBetween(String type, Integer generation, Integer minPrice, Integer maxPrice);

    List<Pokemon> findByGenerationAndPriceBetween(Integer generation, Integer minPrice, Integer maxPrice);

    List<Pokemon> findByPriceBetween(Integer minPrice, Integer maxPrice);

    List<Pokemon> findByNameContainingAndGeneration(String name, Integer generation);

    List<Pokemon> findByNameContainingAndPriceBetween(String name, Integer minPrice, Integer maxPrice);

    List<Pokemon> findByTypeAndPriceBetween(String type, Integer minPrice, Integer maxPrice);

    List<Pokemon> findByTypeAndGeneration(String type, Integer generation);

    List<Pokemon> findByGenerationAndType(Integer generation, String type);

    Page<Pokemon> findAll(Pageable pageable);
}
