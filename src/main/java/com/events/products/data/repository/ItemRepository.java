package com.events.products.data.repository;

import com.events.products.data.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    boolean existsByName(String name);
}
