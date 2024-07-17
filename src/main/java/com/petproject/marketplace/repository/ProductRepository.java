package com.petproject.marketplace.repository;

import com.petproject.marketplace.model.Product;
import com.petproject.marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<List<Product>> findByUser(User user);
    Optional<Product> findById(int id);
}