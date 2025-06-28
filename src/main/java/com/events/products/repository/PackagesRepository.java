package com.events.products.repository;

import com.events.products.entity.PackagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackagesRepository extends JpaRepository<PackagesEntity,Long> {
}
