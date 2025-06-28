package com.events.products.business.service;

import com.events.products.business.exception.StoreAlreadyExistsException;
import com.events.products.business.exception.StoreNotFoundException;
import com.events.products.dto.StoreDto;
import com.events.products.entity.StoreEntity;
import com.events.products.repository.StoreRepository;
import com.events.products.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.events.products.utils.Mapper.mapStoreDtoToEntity;
import static com.events.products.utils.Mapper.mapStoreEntityToDto;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    private static void validateStoreNameNotExists(StoreDto dto, StoreRepository repository) {
        if (repository.findByName(dto.getName()) != null) {
            throw new StoreAlreadyExistsException("Store already exists with name: " + dto.getName());
        }
    }

    public StoreDto addStore(StoreDto storeDto) {
        validateAddStoreDto(storeDto);
        validateStoreNameNotExists(storeDto, storeRepository);
        StoreEntity entity = mapStoreDtoToEntity(storeDto);
        StoreEntity saved = storeRepository.save(entity);
        return mapStoreEntityToDto(saved);
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
        return mapStoreEntityToDto(updated);
    }

    public List<StoreDto> getStores() {
        return storeRepository.findAll().stream()
                .map(Mapper::mapStoreEntityToDto)
                .toList();
    }

    public StoreDto getStoreById(Long id) {
        StoreEntity store = storeRepository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id: " + id));
        return mapStoreEntityToDto(store);
    }

    public StoreDto getStoreByName(String name) {
        StoreEntity store = storeRepository.findByName(name);
        if (store == null) {
            throw new StoreNotFoundException("Store not found with name: " + name);
        }
        return mapStoreEntityToDto(store);
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
