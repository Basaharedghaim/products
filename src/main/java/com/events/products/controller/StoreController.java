package com.events.products.controller;

import com.events.products.business.service.StoreService;
import com.events.products.data.dto.StoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store/api/v1")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/add")
    public ResponseEntity<StoreDto> addStore(@RequestBody StoreDto storeDto) {
        StoreDto created = storeService.addStore(storeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok("Store Deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StoreDto> updateStore(@PathVariable Long id, @RequestBody StoreDto storeDto) {
        return ResponseEntity.ok(storeService.updateStore(id, storeDto));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<StoreDto>> getStores() {
        return ResponseEntity.ok(storeService.getStores());
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id) {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @GetMapping("/by-name")
    public ResponseEntity<StoreDto> getStoreByName(@RequestParam String name) {
        return ResponseEntity.ok(storeService.getStoreDtoByName(name));
    }

    @PostMapping("/{storeId}/subcategories/{subCategoryId}")
    public ResponseEntity<String> addSubCategoryToStore(
            @PathVariable Long storeId,
            @PathVariable Long subCategoryId) {

        storeService.addSubCategoryToStore(storeId, subCategoryId);
        return ResponseEntity.ok("SubCategory added to Store successfully");
    }


}
