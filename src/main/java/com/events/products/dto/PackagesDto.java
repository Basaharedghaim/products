package com.events.products.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackagesDto {
    private Long id;
    private String name;
    private String description;
}
