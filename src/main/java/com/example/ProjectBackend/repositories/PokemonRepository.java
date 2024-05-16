package com.example.ProjectBackend.repositories;

import com.example.ProjectBackend.entities.Pokemon;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
    Optional<Pokemon> findById(Integer id);
}
