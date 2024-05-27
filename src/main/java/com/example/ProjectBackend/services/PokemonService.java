package com.example.ProjectBackend.services;

import com.example.ProjectBackend.dtos.PokemonDto;
import com.example.ProjectBackend.entities.Pokemon;
import com.example.ProjectBackend.exceptions.PokemonAlreadyExistsException;
import com.example.ProjectBackend.exceptions.PokemonNotFoundException;
import com.example.ProjectBackend.repositories.PokemonRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<PokemonDto> allProducts() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return pokemons.stream()
                .map(pokemon -> modelMapper.map(pokemon, PokemonDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public Pokemon createPokemon(PokemonDto input) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(input.getId());
        if(optionalPokemon.isPresent()){
            throw new PokemonAlreadyExistsException();
        }else {
            Pokemon pokemon = modelMapper.map(input, Pokemon.class);
            return pokemonRepository.save(pokemon);
        }

    }

    public Pokemon updatePokemon(Integer id, PokemonDto input) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        Optional<Pokemon> duplicatePokemon = pokemonRepository.findById(input.getId());
        if (optionalPokemon.isPresent()) {
            Pokemon pokemon = optionalPokemon.get();
            modelMapper.map(input, pokemon);
            return pokemonRepository.save(pokemon);
        }
        if (duplicatePokemon.isPresent() && !duplicatePokemon.get().getId().equals(id)) {
            throw new PokemonAlreadyExistsException();
        } else {
            throw new PokemonNotFoundException();
        }
    }

    public void deletePokemonById(Integer id) {
        pokemonRepository.deleteById(id);
    }

    public List<PokemonDto> getPokemonByName(String name) {
        List<Pokemon> pokemons = pokemonRepository.findByNameContaining(name);
        return pokemons.stream()
                .map(pokemon -> modelMapper.map(pokemon, PokemonDto.class))
                .collect(Collectors.toList());
    }

    public Page<PokemonDto> getPagedPokemon(String name, String type, Integer generation, Integer minPrice, Integer maxPrice, Pageable pageable) {
        return pokemonRepository.findFilteredPokemon(name, type, generation, minPrice, maxPrice, pageable)
                .map(pokemon -> modelMapper.map(pokemon, PokemonDto.class));
    }
}
