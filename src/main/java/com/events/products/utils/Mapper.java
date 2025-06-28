package com.events.products.utils;

import com.events.products.data.dto.CategoryDto;
import com.events.products.data.entity.CategoryEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mapper {
    private Mapper() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static CategoryEntity mapCategoryDtoToEntity(CategoryDto categoryDto) {
        return CategoryEntity.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
    }

    public static CategoryDto mapCategoryEntityToDto(CategoryEntity categoryEntity) {
        return CategoryDto.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .build();
    }
}
