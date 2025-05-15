package com.invextory.repositories;

import com.invextory.models.Category;
import com.invextory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);

    Optional<Product> findBySku(String sku);

    List<Product> findByCategory(Category category);

    boolean existsBySku(String sku);

}
