package com.example.ProjectBackend.controllers;

import com.example.ProjectBackend.dtos.PokemonDto;
import com.example.ProjectBackend.entities.Pokemon;
import com.example.ProjectBackend.services.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Pokemon> createPokemon(@RequestBody PokemonDto pokemonDto){
        Pokemon createPokemon = pokemonService.createPokemon(pokemonDto);
        return ResponseEntity.ok(createPokemon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pokemon> deletePokemon(@PathVariable Integer id){
        pokemonService.deletePokemonById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable Integer id, @RequestBody PokemonDto pokemonDto){
        Pokemon updated = pokemonService.updatePokemon(id, pokemonDto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Pokemon>> getPokemonByName(@PathVariable String name){
        List<Pokemon> pokemonList = pokemonService.getPokemonByName(name);
        return ResponseEntity.ok(pokemonList);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Pokemon>> getFilteredPokemon(@RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String type,
                                                            @RequestParam(required = false) Integer generation,
                                                            @RequestParam(required = false) Integer minPrice,
                                                            @RequestParam(required = false) Integer maxPrice){
        List<Pokemon> pokemonList = pokemonService.getFilteredPokemon(name, type, generation, minPrice, maxPrice);
        return ResponseEntity.ok(pokemonList);
    }
}
