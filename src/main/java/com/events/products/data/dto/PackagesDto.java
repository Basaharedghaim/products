package com.events.products.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class PackagesDto {
    private Long id;
    private String name;
    private String description;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime updatedOn;
}
