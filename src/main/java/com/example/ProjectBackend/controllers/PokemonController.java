package com.example.ProjectBackend.controllers;

import com.example.ProjectBackend.entities.Pokemon;
import com.example.ProjectBackend.services.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pokemon")
public class PokemonController {
    private final PokemonService pokemonService;


    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> allProducts(){
        List <Pokemon> pokemons = pokemonService.allProducts();

        return ResponseEntity.ok(pokemons);
    }
}
