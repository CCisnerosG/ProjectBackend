package com.example.ProjectBackend.controllers;

import com.example.ProjectBackend.entities.ShoppingCart;
import com.example.ProjectBackend.entities.ShoppingCartProduct;
import com.example.ProjectBackend.entities.User;
import com.example.ProjectBackend.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shoppingCart")
@PreAuthorize("isAuthenticated()")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;


    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Integer pokemonId, @RequestParam Integer quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal(); //TODO HACER ESTO EN TODO LO QUE SEA REQURESTPARAM DE USERID

            shoppingCartService.addToShoppingCart(currentUser.getId(), pokemonId, quantity);
            return ResponseEntity.ok("Item added to cart successfully");
    }

    @GetMapping("/products")
    public ResponseEntity<List<ShoppingCartProduct>> getAllProducts (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal(); //TODO HACER ESTO EN TODO LO QUE SEA REQURESTPARAM DE USERID

        List<ShoppingCartProduct> products = shoppingCartService.getAllProducts(currentUser.getId());
        return ResponseEntity.ok(products);
    }

    @PostMapping("/moveFromWishlist")
    public ResponseEntity<Void> moveFromWishlist() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal(); //TODO HACER ESTO EN TODO LO QUE SEA REQURESTPARAM DE USERID

        shoppingCartService.fromWishlistToCart(currentUser.getId());
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

    @GetMapping("/byUser")
    public ResponseEntity<ShoppingCart> getShoppingCartByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByUserId(currentUser.getId());
        return ResponseEntity.ok(shoppingCart);
    }

}
