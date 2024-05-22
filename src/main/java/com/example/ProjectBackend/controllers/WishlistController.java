package com.example.ProjectBackend.controllers;

import com.example.ProjectBackend.dtos.WishlistProductDto;
import com.example.ProjectBackend.entities.ShoppingCartProduct;
import com.example.ProjectBackend.entities.WishlistProduct;
import com.example.ProjectBackend.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/wishlist")
public class WishlistController {
    @Autowired
    WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity<WishlistProductDto> addToWishlist (@RequestParam Long userId, @RequestParam Integer pokemonId){
        WishlistProductDto wishlistProductDto = wishlistService.addToWishlist(userId, pokemonId);
        return ResponseEntity.ok(wishlistProductDto);
    }

    @GetMapping("/products")
    public List<WishlistProduct> getAllProducts(@RequestParam Long userId) {
       return wishlistService.getAllProducts(userId);
    }

    @DeleteMapping("/{pokemonId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer pokemonId){
        wishlistService.deleteProductByPokemonId(pokemonId);
        return ResponseEntity.noContent().build();
    }



}
