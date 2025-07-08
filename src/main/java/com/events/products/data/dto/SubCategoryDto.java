package com.events.products.data.dto;

import com.events.products.data.entity.StoreEntity;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategoryDto {
    private Long id;
    private String name;
    private String description;
    private Set<StoreEntity> stores;

}
