package com.events.products.data.repository;

import com.events.products.data.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {
    SubCategoryEntity findByName(String name);
}
