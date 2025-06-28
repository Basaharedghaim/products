package com.events.products.controller;

import com.events.products.business.service.ProductService;
import com.events.products.data.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product/api/v1")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> addCategory(@RequestBody ProductDto categoryDto) {
        ProductDto created = productService.addProduct(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateCategory(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(id, productDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getCategories() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

}
