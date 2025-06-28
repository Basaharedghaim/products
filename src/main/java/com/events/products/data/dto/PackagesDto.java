package com.events.products.data.dto;

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
