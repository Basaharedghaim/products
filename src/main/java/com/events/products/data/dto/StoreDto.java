package com.events.products.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDto {
    private Long id;
    private String name;
    private String address;
    private double stars;
    private String storeImagePath;
    private String description;

    private Set<Long> categories;
    private Set<Long> subCategories;

    private String createdBy;
    private String updatedBy;
    private LocalDateTime updatedOn;
}
