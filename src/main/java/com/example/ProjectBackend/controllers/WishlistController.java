package com.example.ProjectBackend.controllers;

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
    public ResponseEntity<String> addToWishlist (@RequestParam Long userId, @RequestParam Integer pokemonId){
        wishlistService.addToWishlist(userId, pokemonId);
        return ResponseEntity.ok("Item added to wishlist successfully");
    }

    @GetMapping("/products")
    public ResponseEntity<List<WishlistProduct>> getAllProducts (@RequestParam Long userId){
        List<WishlistProduct> products = wishlistService.getAllProducts(userId);
        return ResponseEntity.ok(products);
    }

//    @DeleteMapping("/delete/{id}")


}
