package com.events.products.repository;

import com.events.products.entity.PackagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagesRepository extends JpaRepository<PackagesEntity, Long> {
}
