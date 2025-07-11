package com.events.products.data.dto;

import com.events.products.data.entity.StoreEntity;
import com.events.products.data.entity.SubCategoryEntity;
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
public class SubCategoryDto {
    private Long id;
    private String name;
    private String description;
    private List<Long> stores;
    private List<Long> items;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime updatedOn;

    public static SubCategoryDto mapSubCategoryEntityToDto(SubCategoryEntity subCategoryEntity) {
        return SubCategoryDto.builder()
                .id(subCategoryEntity.getId())
                .name(subCategoryEntity.getName())
                .description(subCategoryEntity.getDescription())
                .createdBy(subCategoryEntity.getCreatedBy())
                .updatedBy(subCategoryEntity.getUpdatedBy())
                .updatedOn(subCategoryEntity.getUpdatedOn())
                .stores(
                        subCategoryEntity.getStores() != null
                                ? subCategoryEntity.getStores().stream()
                                .map(StoreEntity::getId)
                                .toList()
                                : List.of()
                )
                .build();
    }
}
