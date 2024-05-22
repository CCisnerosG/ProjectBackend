package com.example.ProjectBackend.repositories;

import com.example.ProjectBackend.entities.Role;
import com.example.ProjectBackend.entities.State;
import com.example.ProjectBackend.entities.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StateRepository extends JpaRepository<State, Integer> {
    Optional<State> findByName(StateEnum name);
}
