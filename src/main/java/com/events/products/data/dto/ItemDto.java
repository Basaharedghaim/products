package com.events.products.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private float price;
    private List<Long> subCategories;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime updatedOn;
}
