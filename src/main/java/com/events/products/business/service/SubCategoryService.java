package com.events.products.business.service;

import com.events.products.business.exception.ItemNotFoundException;
import com.events.products.business.exception.StoreAlreadyExistsException;
import com.events.products.business.exception.SubCategoryNotFoundException;
import com.events.products.data.dto.SubCategoryDto;
import com.events.products.data.entity.ItemEntity;
import com.events.products.data.entity.SubCategoryEntity;
import com.events.products.data.repository.ItemRepository;
import com.events.products.data.repository.SubCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final ItemRepository itemRepository;
    private final ObjectMapper objectMapper;

    public SubCategoryDto addSubCategory(SubCategoryDto dto) {
        validateSubCategoryNameDoesNotExist(dto);
        SubCategoryEntity entity = objectMapper.convertValue(dto, SubCategoryEntity.class);
        SubCategoryEntity saved = subCategoryRepository.save(entity);
        return mapSubCategoryEntityToDto(saved);
    }

    public SubCategoryDto updateSubCategory(Long id, SubCategoryDto dto) {
        SubCategoryEntity entity = subCategoryRepository.findById(id)
                .orElseThrow(() -> new SubCategoryNotFoundException("SubCategory not found with id: " + id));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        SubCategoryEntity updated = subCategoryRepository.save(entity);
        return mapSubCategoryEntityToDto(updated);
    }

    public List<SubCategoryDto> getAll() {
        return subCategoryRepository.findAll().stream()
                .map(i -> objectMapper.convertValue(i, SubCategoryDto.class))
                .collect(toList());
    }

    public SubCategoryDto getById(Long id) {
        SubCategoryEntity entity = subCategoryRepository.findById(id)
                .orElseThrow(() -> new SubCategoryNotFoundException("SubCategory not found with id: " + id));
        return mapSubCategoryEntityToDto(entity);
    }


    public void delete(Long id) {
        if (!subCategoryRepository.existsById(id)) {
            throw new SubCategoryNotFoundException("SubCategory not found with id: " + id);
        }
        subCategoryRepository.deleteById(id);
    }

    public void addSubCategoryToStore(Long subCategoryId, Long itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Store Not Found"));

        SubCategoryEntity subCategory = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new SubCategoryNotFoundException("Sub Category Not Found"));

        subCategory.getItems().add(itemEntity);
        subCategoryRepository.save(subCategory);
    }


    private SubCategoryDto mapSubCategoryEntityToDto(SubCategoryEntity subCategoryEntity) {
        return SubCategoryDto.mapSubCategoryEntityToDto(subCategoryEntity);
    }

    private void validateSubCategoryNameDoesNotExist(SubCategoryDto dto) {
        if (subCategoryRepository.findByName(dto.getName()) != null) {
            throw new StoreAlreadyExistsException("SubCategory already exists with name: " + dto.getName());
        }
    }


}
