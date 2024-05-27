package com.example.ProjectBackend.services;

import com.example.ProjectBackend.dtos.WishlistProductDto;
import com.example.ProjectBackend.entities.*;
import com.example.ProjectBackend.exceptions.PokemonNotFoundException;
import com.example.ProjectBackend.exceptions.ShoppingCartNotFound;
import com.example.ProjectBackend.exceptions.UserNotFoundException;
import com.example.ProjectBackend.repositories.*;
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

    @Autowired
    private WishlistRepository wishlistRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCart getShoppingCartByUserId(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        if (cart == null) {
            throw new ShoppingCartNotFound();
        }
        return cart;
    }

    public List<ShoppingCartProduct> getAllProducts(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
        return shoppingCartProductRepository.findByShoppingCartId(cart.getId());
    }

    public void addToShoppingCart(Long userId, Integer pokemonId, Integer quantity) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
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
            Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(PokemonNotFoundException::new);
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
    public void fromWishlistToCart(Long userId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId);
        List<WishlistProduct> wishlistProducts = wishlistService.getAllProducts(userId);

        for (WishlistProduct product : wishlistProducts) {
            addToShoppingCart(userId, product.getPokemon().getId(), 1);
        }

        wishlistService.emptyWishlist(wishlist.getId());
    }

    @Transactional
    public void deleteProductByPokemonId(Integer pokemonId) {
        shoppingCartProductRepository.deleteByPokemonId(pokemonId);
    }

    public void updateQuantity(Integer pokemonId, Integer newQuantity) {
        ShoppingCartProduct product = shoppingCartProductRepository.findByPokemonId(pokemonId);
        product.setQuantity(newQuantity);
        shoppingCartProductRepository.save(product);
    }

    @Transactional
    public void emptyShoppingCart(Long shoppingCartId) {
        shoppingCartProductRepository.deleteByShoppingCartId(shoppingCartId);
    }

}