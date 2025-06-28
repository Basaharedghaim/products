package com.events.products.controller;

import com.events.products.business.service.PackagesService;
import com.events.products.business.service.ProductService;
import com.events.products.dto.PackagesDto;
import com.events.products.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RequestMapping("/package/api/v1")
public class PackagesController {
    private final PackagesService productService;

    @PostMapping
    public ResponseEntity<PackagesDto> addPackages(@RequestBody PackagesDto packagesDto) {
        PackagesDto created = productService.addPackages(packagesDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackage(@PathVariable Long id) {
        productService.deletePackage(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackagesDto> updatePackage(@PathVariable Long id, @RequestBody PackagesDto productDto) {
        return ResponseEntity.ok(productService.updatePackage(id, productDto));
    }

    @GetMapping
    public ResponseEntity<List<PackagesDto>> getPackages() {
        return ResponseEntity.ok(productService.getPackages());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PackagesDto> getPackageById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getPackageById(id));
    }
}
