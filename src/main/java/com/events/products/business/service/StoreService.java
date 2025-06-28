package com.events.products.business.service;

import com.events.products.business.exception.StoreAlreadyExistsException;
import com.events.products.business.exception.StoreNotFoundException;
import com.events.products.data.dto.StoreDto;
import com.events.products.data.entity.StoreEntity;
import com.events.products.data.repository.StoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final ObjectMapper objectMapper;

    private static void validateStoreNameNotExists(StoreDto dto, StoreRepository repository) {
        if (repository.findByName(dto.getName()) != null) {
            throw new StoreAlreadyExistsException("Store already exists with name: " + dto.getName());
        }
    }

    public StoreDto addStore(StoreDto storeDto) {
        validateAddStoreDto(storeDto);
        validateStoreNameNotExists(storeDto, storeRepository);
        StoreEntity entity = objectMapper.convertValue(storeDto, StoreEntity.class);
        StoreEntity saved = storeRepository.save(entity);
        return convertEntityToDto(saved);
    }

        public void deleteStore(Long id) {
        if (!storeRepository.existsById(id)) {
            throw new StoreNotFoundException("Store not found with id: " + id);
        }
        storeRepository.deleteById(id);
    }

    public StoreDto updateStore(Long id, StoreDto storeDto) {
        StoreEntity existing = storeRepository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id: " + id));

        updateEntityFromDto(existing, storeDto);
        StoreEntity updated = storeRepository.save(existing);
        return convertEntityToDto(updated);
    }

    private StoreDto convertEntityToDto(StoreEntity updated) {
        return objectMapper.convertValue(updated, StoreDto.class);
    }

    public List<StoreDto> getStores() {
        return storeRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    public StoreDto getStoreById(Long id) {
        StoreEntity store = storeRepository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id: " + id));
        return convertEntityToDto(store);
    }

    public StoreDto getStoreByName(String name) {
        StoreEntity store = storeRepository.findByName(name);
        if (store == null) {
            throw new StoreNotFoundException("Store not found with name: " + name);
        }
        return convertEntityToDto(store);
    }

    private void updateEntityFromDto(StoreEntity entity, StoreDto dto) {
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
    }

    private void validateAddStoreDto(StoreDto dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Store name is required");
        }
    }
}
