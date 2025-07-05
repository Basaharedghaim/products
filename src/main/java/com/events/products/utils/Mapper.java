package com.events.products.utils;

import com.events.products.data.dto.CategoryDto;
import com.events.products.data.entity.CategoryEntity;
import com.events.products.data.dto.CategoryDto;
import com.events.products.data.dto.SubCategoryDto;
import com.events.products.data.entity.CategoryEntity;
import com.events.products.data.entity.SubCategoryEntity;
import lombok.extern.slf4j.Slf4j;

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
    public static SubCategoryDto mapSubCategoryEntityToDto(SubCategoryEntity entity) {
        return SubCategoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public static SubCategoryEntity mapSubCategoryDtoToEntity(SubCategoryDto dto) {
        return SubCategoryEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

}
