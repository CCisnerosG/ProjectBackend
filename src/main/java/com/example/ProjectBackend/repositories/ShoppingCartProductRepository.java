package com.example.ProjectBackend.repositories;

import com.example.ProjectBackend.entities.ShoppingCartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProduct, Long> {
    ShoppingCartProduct findByShoppingCartIdAndPokemonId(Long shoppingCartId, Integer pokemonId);
    List<ShoppingCartProduct> findByShoppingCartId (Long shoppingCartId);
    void deleteByShoppingCartId (Long shoppingCartId);
}
