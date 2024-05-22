package com.example.ProjectBackend.repositories;

import com.example.ProjectBackend.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUserId(Long userId);
    void deleteById(Long wishlistId);

}
