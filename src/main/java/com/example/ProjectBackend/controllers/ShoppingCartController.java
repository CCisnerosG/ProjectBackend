package com.example.ProjectBackend.controllers;

import com.example.ProjectBackend.entities.ShoppingCartProduct;
import com.example.ProjectBackend.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shoppingCart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Long userId, @RequestParam Integer pokemonId, @RequestParam Integer quantity) {
            shoppingCartService.addToShoppingCart(userId, pokemonId, quantity);
            return ResponseEntity.ok("Item added to cart successfully");
    }

    @GetMapping("/products")
    public ResponseEntity<List<ShoppingCartProduct>> getAllProducts (@RequestParam Long userId){
        List<ShoppingCartProduct> products = shoppingCartService.getAllProducts(userId);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/moveFromWishlist")
    public ResponseEntity<Void> moveFromWishlist(@RequestParam Long userId) {
        shoppingCartService.fromWishlistToCart(userId);
        return ResponseEntity.ok().build();
    }
}
