package com.events.products.business.service;

import com.events.products.business.exception.StoreAlreadyExistsException;
import com.events.products.business.exception.SubCategoryNotFoundException;
import com.events.products.data.dto.SubCategoryDto;
import com.events.products.data.entity.SubCategoryEntity;
import com.events.products.data.repository.SubCategoryRepository;
import com.events.products.utils.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubCategoryRepository repository;
    private final ObjectMapper objectMapper;

    public SubCategoryDto addSubCategory(SubCategoryDto dto) {
        validateSubCategoryNameDoesntExist(dto);
        SubCategoryEntity entity = Mapper.mapSubCategoryDtoToEntity(dto);
        SubCategoryEntity saved = repository.save(entity);
        return mapSubCategoryEntityToDto(saved);
    }

    public SubCategoryDto updateSubCategory(Long id, SubCategoryDto dto) {
        SubCategoryEntity entity = repository.findById(id)
                .orElseThrow(() -> new SubCategoryNotFoundException("SubCategory not found with id: " + id));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        SubCategoryEntity updated = repository.save(entity);
        return mapSubCategoryEntityToDto(updated);
    }

    public List<SubCategoryDto> getAll() {
        return repository.findAll().stream()
                .map(this::mapSubCategoryEntityToDto)
                .collect(Collectors.toList());
    }

    public SubCategoryDto getById(Long id) {
        SubCategoryEntity entity = repository.findById(id)
                .orElseThrow(() -> new SubCategoryNotFoundException("SubCategory not found with id: " + id));
        return mapSubCategoryEntityToDto(entity);
    }
    public SubCategoryDto mapSubCategoryEntityToDto(SubCategoryEntity subCategoryEntity){
        return objectMapper.convertValue(subCategoryEntity,SubCategoryDto.class);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new SubCategoryNotFoundException("SubCategory not found with id: " + id);
        }
        repository.deleteById(id);
    }
    private void validateSubCategoryNameDoesntExist(SubCategoryDto dto) {
        if (repository.findByName(dto.getName()) != null) {
            throw new StoreAlreadyExistsException("SubCategory already exists with name: " + dto.getName());
        }
    }
}
