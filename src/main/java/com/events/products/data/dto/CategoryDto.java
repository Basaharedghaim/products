package com.events.products.data.dto;

import com.events.products.data.entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private String name;
    private Long id;
    private String description;
    private Set<StoreEntity> stores;
}
