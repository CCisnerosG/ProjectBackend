package com.example.ProjectBackend.services;

import com.example.ProjectBackend.dtos.PokemonDto;

import com.example.ProjectBackend.entities.Pokemon;
import com.example.ProjectBackend.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {
    @Autowired
    PokemonRepository pokemonRepository;

    public PokemonService() {
    }

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> allProducts() {
        List<Pokemon> pokemons = new ArrayList<>();

        pokemonRepository.findAll().forEach(pokemons::add);
        return pokemons;
    }


    public Pokemon createPokemon(PokemonDto input) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(input.getId());
        pokemon.setName(input.getName());
        pokemon.setSprite(input.getSprite());
        pokemon.setType(input.getType());
        pokemon.setWeight(input.getWeight());
        pokemon.setHeight(input.getHeight());
        pokemon.setPrice(input.getPrice());
        pokemon.setSubtotal(input.getSubtotal());
        pokemon.setTaxes(input.getTaxes());
        pokemon.setSave(input.getSave());
        pokemon.setDescription(input.getDescription());
        pokemon.setGeneration(input.getGeneration());
        pokemon.setCries(input.getCries());
        pokemon.setLegendary(input.getLegendary());
//        pokemon.setEvolutions(input.getEvolutions());

        return pokemonRepository.save(pokemon);
    }

    public void deletePokemonById(Integer id) {
        pokemonRepository.deleteById(id);
    }

    public Pokemon updatePokemon(Integer id, PokemonDto input) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        if (optionalPokemon.isPresent()) {
            Pokemon pokemon = optionalPokemon.get();
            pokemon.setName(input.getName());
            pokemon.setSprite(input.getSprite());
            pokemon.setType(input.getType());
            pokemon.setWeight(input.getWeight());
            pokemon.setHeight(input.getHeight());
            pokemon.setPrice(input.getPrice());
            pokemon.setSubtotal(input.getSubtotal());
            pokemon.setTaxes(input.getTaxes());
            pokemon.setSave(input.getSave());
            pokemon.setDescription(input.getDescription());
            pokemon.setGeneration(input.getGeneration());
            pokemon.setCries(input.getCries());
            pokemon.setLegendary(input.getLegendary());

            return pokemonRepository.save(pokemon);
        } else {
            throw new RuntimeException("Pokemon not found with id " + id);
        }
    }

    public List<Pokemon> getPokemonByName (String name){
        return pokemonRepository.findByNameContaining(name);
    }

    public List<Pokemon> getFilteredPokemon(String name, String type, Integer generation, Integer minPrice, Integer maxPrice) {
        if (name != null && type != null && generation != null && minPrice != null && maxPrice != null) {
            return pokemonRepository.findByNameContainingAndTypeAndGenerationAndPriceBetween(name, type, generation, minPrice, maxPrice);
        } else if (name != null && type != null && generation != null) {
            return pokemonRepository.findByNameContainingAndTypeAndGeneration(name, type, generation);
        } else if (name != null && type != null) {
            return pokemonRepository.findByNameContainingAndType(name, type);
        } else if (name != null && generation != null) {
            return pokemonRepository.findByNameContainingAndGeneration(name, generation);
        } else if (name != null && minPrice != null && maxPrice != null) {
            return pokemonRepository.findByNameContainingAndPriceBetween(name, minPrice, maxPrice);
        } else if (type != null && generation != null && minPrice != null && maxPrice != null) {
            return pokemonRepository.findByTypeAndGenerationAndPriceBetween(type, generation, minPrice, maxPrice);
        } else if (type != null && generation != null) {
            return pokemonRepository.findByTypeAndGeneration(type, generation);
        } else if (type != null && minPrice != null && maxPrice != null) {
            return pokemonRepository.findByTypeAndPriceBetween(type, minPrice, maxPrice);
        } else if (generation != null && minPrice != null && maxPrice != null) {
            return pokemonRepository.findByGenerationAndPriceBetween(generation, minPrice, maxPrice);
        } else if (minPrice != null && maxPrice != null) {
            return pokemonRepository.findByPriceBetween(minPrice, maxPrice);
        } else if (name != null) {
            return pokemonRepository.findByNameContaining(name);
        } else if (type != null) {
            return pokemonRepository.findByType(type);
        } else if (generation != null) {
            return pokemonRepository.findByGeneration(generation);
        } else {
            return pokemonRepository.findAll();
        }
    }

}
