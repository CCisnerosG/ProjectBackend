package com.example.ProjectBackend.controllers;

import com.example.ProjectBackend.dtos.WishlistProductDto;
import com.example.ProjectBackend.entities.ShoppingCartProduct;
import com.example.ProjectBackend.entities.User;
import com.example.ProjectBackend.entities.WishlistProduct;
import com.example.ProjectBackend.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/wishlist")
@PreAuthorize("isAuthenticated()")
public class WishlistController {
    @Autowired
    WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity<WishlistProductDto> addToWishlist (@RequestParam Integer pokemonId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal(); //TODO HACER ESTO EN TODO LO QUE SEA REQURESTPARAM DE USERID
        WishlistProductDto wishlistProductDto = wishlistService.addToWishlist(currentUser.getId(), pokemonId);
        return ResponseEntity.ok(wishlistProductDto);
    }

    @GetMapping("/products")
    public List<WishlistProduct> getAllProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal(); //TODO HACER ESTO EN TODO LO QUE SEA REQURESTPARAM DE USERID

       return wishlistService.getAllProducts(currentUser.getId());
    }

    @DeleteMapping("/{pokemonId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer pokemonId){
        wishlistService.deleteProductByPokemonId(pokemonId);
        return ResponseEntity.noContent().build();
    }

}
