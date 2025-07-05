package com.events.products.data.dto;

import com.events.products.data.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDto {
    private String name;
    private Long id;
    private String address;
    private double stars;
    private String storeImagePath;
    private String description;
//    private Set<CategoryEntity> categories = new HashSet<>();
}

