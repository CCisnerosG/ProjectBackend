package com.example.ProjectBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PokemonIdNullException extends RuntimeException {
    public PokemonIdNullException() {
        super("Pokemon number cannot be empty");
    }
}
