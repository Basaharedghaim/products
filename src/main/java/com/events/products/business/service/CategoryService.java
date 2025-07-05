package com.events.products.business.service;

import com.events.products.business.exception.CategoryAlreadyExistsException;
import com.events.products.business.exception.CategoryNotFoundException;
import com.events.products.data.dto.CategoryDto;
import com.events.products.data.entity.CategoryEntity;
import com.events.products.data.entity.StoreEntity;
import com.events.products.data.repository.CategoryRepository;
import com.events.products.data.repository.StoreRepository;
import com.events.products.utils.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.events.products.utils.Mapper.mapCategoryDtoToEntity;
import static com.events.products.utils.Mapper.mapCategoryEntityToDto;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;
    private final ObjectMapper objectMapper;

    public CategoryDto addCategory(CategoryDto categoryDto) {
        validateAddCategoryDto(categoryDto);
        validateCategoryNameNotExists(categoryDto, categoryRepository);
        CategoryEntity entity = mapCategoryDtoToEntity(categoryDto);
        CategoryEntity saved = categoryRepository.save(entity);
        return mapCategoryEntityToDto(saved);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        CategoryEntity existing = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        updateEntityFromDto(existing, categoryDto);
        CategoryEntity updated = categoryRepository.save(existing);
        return mapCategoryEntityToDto(updated);
    }

    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapCategoryEntityToDto)
                .toList();
    }
    private CategoryDto mapCategoryEntityToDto(CategoryEntity categoryEntity){
        return objectMapper.convertValue(categoryEntity,CategoryDto.class);
    }

    public CategoryDto getCategoryById(Long id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        return mapCategoryEntityToDto(category);
    }

    public CategoryDto getCategoryByName(String name) {
        CategoryEntity category = categoryRepository.findByName(name);
        if (category == null) {
            throw new CategoryNotFoundException("Category not found with name: " + name);
        }
        return mapCategoryEntityToDto(category);
    }

    private void updateEntityFromDto(CategoryEntity entity, CategoryDto dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
    }

    private void validateAddCategoryDto(CategoryDto dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }
    }
    private static void validateCategoryNameNotExists(CategoryDto dto, CategoryRepository repository) {
        if (repository.findByName(dto.getName()) != null) {
            throw new CategoryAlreadyExistsException("Category already exists with name: " + dto.getName());
        }
    }

    public CategoryDto addStoreToCategory(Long categoryId, Long storeId) {
        // 1. Fetch the category
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // 2. Fetch the store
        StoreEntity store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        // 3. Add the store to the category's set
        category.getStores().add(store);

        // 4. Save the updated category
        CategoryEntity updatedCategory = categoryRepository.save(category);

        // 5. Convert to DTO and return
        return CategoryDto.builder()
                .id(updatedCategory.getId())
                .name(updatedCategory.getName())
                .description(updatedCategory.getDescription())
                .stores(updatedCategory.getStores())
                .build();
    }

}


