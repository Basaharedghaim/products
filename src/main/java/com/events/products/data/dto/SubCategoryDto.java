package com.events.products.data.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategoryDto {
    private Long id;
    private String name;
    private String description;
}
