package com.events.products.data.repository;

import com.events.products.data.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    StoreEntity findByName(String name);

}
