package com.events.products.data.repository;

import com.events.products.data.entity.PackagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagesRepository extends JpaRepository<PackagesEntity, Long> {
}
