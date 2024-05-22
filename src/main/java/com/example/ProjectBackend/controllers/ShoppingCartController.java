package com.example.ProjectBackend.controllers;

import com.example.ProjectBackend.entities.ShoppingCartProduct;
import com.example.ProjectBackend.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/{pokemonId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer pokemonId){
        shoppingCartService.deleteProductByPokemonId(pokemonId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{pokemonId}")
    public ResponseEntity<String> updateProductQuantity(@PathVariable Integer pokemonId, @RequestParam Integer quantity) {
        try {
            shoppingCartService.updateQuantity(pokemonId, quantity);
            return ResponseEntity.ok("Product quantity updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product quantity: " + e.getMessage());
        }
    }

}
