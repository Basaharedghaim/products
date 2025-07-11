package com.events.products.business.service;

import com.events.products.business.exception.StoreAlreadyExistsException;
import com.events.products.business.exception.StoreNotFoundException;
import com.events.products.business.exception.SubCategoryNotFoundException;
import com.events.products.data.dto.StoreDto;
import com.events.products.data.entity.StoreEntity;
import com.events.products.data.entity.SubCategoryEntity;
import com.events.products.data.repository.StoreRepository;
import com.events.products.data.repository.SubCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ObjectMapper objectMapper;

    public StoreDto addStore(StoreDto storeDto) {
        validateAddStoreDto(storeDto);
        validateStoreNameNotExists(storeDto);

        StoreEntity saved = storeRepository.save(convertDtoToEntity(storeDto));
        return convertEntityToDto(saved);
    }

    public void deleteStore(Long id) {
        validateIfIdExist(id);
        storeRepository.deleteById(id);
    }

    public StoreDto updateStore(Long id, StoreDto storeDto) {
        validateIfIdExist(id);
        validateStoreNameNotExists(storeDto);
        StoreEntity store = getStoreEntity(id);
        store.setName(storeDto.getName());
        StoreEntity updated = storeRepository.save(store);
        return convertEntityToDto(updated);
    }

    public List<StoreDto> getStores() {
        return storeRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    public StoreDto getStoreById(Long id) {
        StoreEntity store = getStoreEntity(id);
        return convertEntityToDto(store);
    }

    public StoreDto getStoreDtoByName(String name) {
        StoreEntity store = storeRepository.findByName(name);
        if (store == null) {
            throw new StoreNotFoundException("Store not found with name: " + name);
        }
        return convertEntityToDto(store);
    }

    public void addSubCategoryToStore(Long storeId, Long subCategoryId) {
        StoreEntity store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store Not Found"));

        SubCategoryEntity subCategory = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new SubCategoryNotFoundException("Sub Category Not Found"));

        store.getSubCategories().add(subCategory);
        storeRepository.save(store);
    }

    private void validateAddStoreDto(StoreDto dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new StoreNotFoundException("Store name is required");
        }

    }

    private void validateStoreNameNotExists(StoreDto dto) {
        if (storeRepository.findByName(dto.getName()) != null) {
            throw new StoreAlreadyExistsException("Store already exists with name: " + dto.getName());
        }
    }

    private void validateIfIdExist(Long id) {
        if (!storeRepository.existsById(id)) {
            throw new StoreNotFoundException("Store not found with id: " + id);
        }
    }

    private StoreEntity getStoreEntity(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id: " + id));

    }

    private StoreDto convertEntityToDto(StoreEntity updated) {
        return objectMapper.convertValue(updated, StoreDto.class);
    }

    private StoreEntity convertDtoToEntity(StoreDto storeDto) {
        return objectMapper.convertValue(storeDto, StoreEntity.class);
    }
}
