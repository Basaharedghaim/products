package com.events.products.business.service;

import com.events.products.business.exception.ItemNotFoundException;
import com.events.products.data.dto.ItemDto;
import com.events.products.data.entity.ItemEntity;
import com.events.products.data.entity.SubCategoryEntity;
import com.events.products.data.repository.ItemRepository;
import com.events.products.data.repository.SubCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ObjectMapper objectMapper;

    public ItemDto addItem(ItemDto itemDto) {
        validateAddProductDto(itemDto);

        ItemEntity entity = objectMapper.convertValue(itemDto, ItemEntity.class);

        ItemEntity saved = itemRepository.save(entity);
        return objectMapper.convertValue(saved, ItemDto.class);
    }

    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ItemNotFoundException("Product not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }

    public ItemDto updateItem(Long id, ItemDto itemDto) {
        ItemEntity existing = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product not found with id: " + id));

        updateEntityFromDto(existing, itemDto);
        ItemEntity updated = itemRepository.save(existing);
        return objectMapper.convertValue(updated, ItemDto.class);
    }

    public List<ItemDto> getItems() {
        return itemRepository.findAll().stream()
                .map(i -> objectMapper.convertValue(i, ItemDto.class))
                .toList();
    }

    public ItemDto getItemById(Long id) {
        ItemEntity product = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product not found with id: " + id));
        return objectMapper.convertValue(product, ItemDto.class);
    }

    private void updateEntityFromDto(ItemEntity entity, ItemDto dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        if (dto.getSubCategories() != null) {
            Set<SubCategoryEntity> subCategoryEntities = new HashSet<>(subCategoryRepository.findAllById(dto.getSubCategories()));
            entity.setSubCategoriesForItems(subCategoryEntities);
        }
    }

    private void validateAddProductDto(ItemDto dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (dto.getPrice() < 0) {
            throw new IllegalArgumentException("Product price must be non-negative");
        }
    }

}

