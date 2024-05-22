package com.example.ProjectBackend.repositories;

import com.example.ProjectBackend.entities.WishlistProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistProductRepository extends JpaRepository<WishlistProduct, Long> {
    List<WishlistProduct> findByWishlistId(Long wishlistId);
    WishlistProduct findByWishlistIdAndPokemonId(Long wishlistId, Integer pokemonId);
    void deleteByWishlistId(Long wishlistId);
    void deleteByPokemonId (Integer pokemonId);
}
