package com.events.products.business.service;

import com.events.products.business.exception.ProductNotFoundException;
import com.events.products.data.dto.ProductDto;
import com.events.products.data.entity.CategoryEntity;
import com.events.products.data.entity.ProductEntity;
import com.events.products.data.repository.CategoryRepository;
import com.events.products.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDto addProduct(ProductDto productDto) {
        validateAddProductDto(productDto);

        ProductEntity entity = mapProductDtoToEntity(productDto);
        ProductEntity saved = productRepository.save(entity);
        return mapProductEntityToDto(saved);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        ProductEntity existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        updateEntityFromDto(existing, productDto);
        ProductEntity updated = productRepository.save(existing);
        return mapProductEntityToDto(updated);
    }

    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream()
                .map(this::mapProductEntityToDto)
                .toList();
    }

    public ProductDto getProductById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return mapProductEntityToDto(product);
    }

    private void updateEntityFromDto(ProductEntity entity, ProductDto dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        if (dto.getCategoryIds() != null) {
            Set<CategoryEntity> categories = new HashSet<>(categoryRepository.findAllById(dto.getCategoryIds()));
            entity.setCategories(categories);
        }
    }

    private void validateAddProductDto(ProductDto dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (dto.getPrice() < 0) {
            throw new IllegalArgumentException("Product price must be non-negative");
        }
    }

    private ProductEntity mapProductDtoToEntity(ProductDto dto) {
        Set<CategoryEntity> categories = new HashSet<>(categoryRepository.findAllById(dto.getCategoryIds()));
        return ProductEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .categories(categories)
                .build();
    }

    private ProductDto mapProductEntityToDto(ProductEntity entity) {
        List<Long> categoryIds = entity.getCategories().stream()
                .map(CategoryEntity::getId)
                .toList();

        return ProductDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .categoryIds(categoryIds)
                .build();
    }
}

