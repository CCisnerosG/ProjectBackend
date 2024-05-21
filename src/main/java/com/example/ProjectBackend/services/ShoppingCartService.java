package com.example.ProjectBackend.services;

import com.example.ProjectBackend.entities.*;
import com.example.ProjectBackend.repositories.PokemonRepository;
import com.example.ProjectBackend.repositories.ShoppingCartProductRepository;
import com.example.ProjectBackend.repositories.ShoppingCartRepository;
import com.example.ProjectBackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartProductRepository shoppingCartProductRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WishlistService wishlistService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<ShoppingCartProduct> getAllProducts(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        return shoppingCartProductRepository.findByShoppingCartId(cart.getId());
    }

    public void addToShoppingCart(Long userId, Integer pokemonId, Integer quantity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUser(user);
            shoppingCartRepository.save(cart);
        }

        ShoppingCartProduct cartProduct = shoppingCartProductRepository.findByShoppingCartIdAndPokemonId(cart.getId(), pokemonId);
        if (cartProduct == null) {
            cartProduct = new ShoppingCartProduct();
            cartProduct.setShoppingCart(cart);
            Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new RuntimeException("Pokemon not found"));
            cartProduct.setPokemon(pokemon);
            cartProduct.setPrice(pokemon.getPrice());
            cartProduct.setQuantity(quantity);
        } else {

            cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
        }
        shoppingCartProductRepository.save(cartProduct);
        shoppingCartRepository.save(cart);
    }

    @Transactional
    public void fromWishlistToCart(Long userId){
        Wishlist wishlist = wishlistService.wishlistRepository.findByUserId(userId);
        List<WishlistProduct> wishlistProducts = wishlistService.getAllProducts(wishlist.getId());

        for (WishlistProduct product : wishlistProducts){
            addToShoppingCart(userId, product.getPokemon().getId(), 1);
        }

        wishlistService.emptyWishlist(wishlist.getId());
        wishlistService.deleteWishlist(wishlist.getId());
    }

}