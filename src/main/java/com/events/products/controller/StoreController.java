package com.events.products.controller;

import com.events.products.business.service.StoreService;
import com.events.products.dto.StoreDto;
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

    @PostMapping
    public ResponseEntity<StoreDto> addStore(@RequestBody StoreDto storeDto) {
        StoreDto created = storeService.addStore(storeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDto> updateStore(@PathVariable Long id, @RequestBody StoreDto storeDto) {
        return ResponseEntity.ok(storeService.updateStore(id, storeDto));
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>> getStores() {
        return ResponseEntity.ok(storeService.getStores());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id) {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<StoreDto> getStoreByName(@PathVariable String name) {
        return ResponseEntity.ok(storeService.getStoreByName(name));
    }
}
