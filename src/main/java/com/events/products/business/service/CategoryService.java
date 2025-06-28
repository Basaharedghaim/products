package com.events.products.business.service;

import com.events.products.business.exception.CategoryAlreadyExistsException;
import com.events.products.business.exception.CategoryNotFoundException;
import com.events.products.data.dto.CategoryDto;
import com.events.products.data.entity.CategoryEntity;
import com.events.products.data.repository.CategoryRepository;
import com.events.products.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.events.products.utils.Mapper.mapCategoryDtoToEntity;
import static com.events.products.utils.Mapper.mapCategoryEntityToDto;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private static void validateCategoryNameNotExists(CategoryDto dto, CategoryRepository repository) {
        if (repository.findByName(dto.getName()) != null) {
            throw new CategoryAlreadyExistsException("Category already exists with name: " + dto.getName());
        }
    }

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
                .map(Mapper::mapCategoryEntityToDto)
                .toList();
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
}


