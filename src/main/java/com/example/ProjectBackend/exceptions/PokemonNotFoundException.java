package com.example.ProjectBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException(){
        super("Pokemon not found");
    }
}
