package com.example.ProjectBackend.controllers;

import com.example.ProjectBackend.dtos.PokemonDto;
import com.example.ProjectBackend.entities.Pokemon;
import com.example.ProjectBackend.services.PokemonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<List<PokemonDto>> allProducts(){
        List <PokemonDto> pokemons = pokemonService.allProducts();

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
    public ResponseEntity<List<PokemonDto>> getPokemonByName(@PathVariable String name){
        List<PokemonDto> pokemonList = pokemonService.getPokemonByName(name);
        return ResponseEntity.ok(pokemonList);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<PokemonDto>> getPagedPokemon(@RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String type,
                                                            @RequestParam(required = false) Integer generation,
                                                            @RequestParam(required = false) Integer minPrice,
                                                            @RequestParam(required = false) Integer maxPrice,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PokemonDto> pokemonPage = pokemonService.getPagedPokemon(name, type, generation, minPrice, maxPrice, pageable);
        return ResponseEntity.ok(pokemonPage);
    }
}
