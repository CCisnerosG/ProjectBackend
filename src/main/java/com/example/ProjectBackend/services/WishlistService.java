package com.example.ProjectBackend.services;

import com.example.ProjectBackend.dtos.WishlistProductDto;
import com.example.ProjectBackend.entities.Pokemon;
import com.example.ProjectBackend.entities.User;
import com.example.ProjectBackend.entities.Wishlist;
import com.example.ProjectBackend.entities.WishlistProduct;
import com.example.ProjectBackend.exceptions.PokemonNotFoundException;
import com.example.ProjectBackend.exceptions.UserNotFoundException;
import com.example.ProjectBackend.repositories.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WishlistProductRepository wishlistProductRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<WishlistProduct> getAllProducts(Long userId){
        Wishlist wishlist = wishlistRepository.findByUserId(userId);
        List<WishlistProduct> products = wishlistProductRepository.findByWishlistId(wishlist.getId());
        return products;
    }

    public WishlistProductDto addToWishlist(Long userId, Integer pokemonId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Wishlist wishlist = wishlistRepository.findByUserId(userId);
        if (wishlist == null){
            wishlist = new Wishlist();
            wishlist.setUser(user);
            wishlistRepository.save(wishlist);
        }

        WishlistProduct wishlistProduct = wishlistProductRepository.findByWishlistIdAndPokemonId(wishlist.getId(), pokemonId);
        if (wishlistProduct == null){
            wishlistProduct = new WishlistProduct();
            wishlistProduct.setWishlist(wishlist);
            Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(PokemonNotFoundException::new);
            wishlistProduct.setPokemon(pokemon);
        }
        wishlistRepository.save(wishlist);
        wishlistProductRepository.save(wishlistProduct);

        WishlistProductDto wishlistProductDto = modelMapper.map(wishlistProduct, WishlistProductDto.class);

        return wishlistProductDto;
    }

    @Transactional
    public void deleteWishlist(Long wishlistId){
        wishlistProductRepository.deleteByWishlistId(wishlistId);
        wishlistRepository.deleteById(wishlistId);
    }

    @Transactional
    public void emptyWishlist(Long wishlistId) {
        wishlistProductRepository.deleteByWishlistId(wishlistId);
    }

    public Wishlist findByUserId(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    @Transactional
    public void deleteProductByPokemonId(Integer pokemonId){
        wishlistProductRepository.deleteByPokemonId(pokemonId);
    }
}
