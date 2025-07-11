package com.events.products.repository;

import com.events.products.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    boolean existsByName(String name);
}
