package com.events.products.controller;

import com.events.products.business.service.PackagesService;
import com.events.products.dto.PackagesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packages")
@RequiredArgsConstructor
public class PackagesController {

    private final PackagesService packagesService;

    @PostMapping
    public ResponseEntity<PackagesDto> create(@RequestBody PackagesDto dto) {
        return new ResponseEntity<>(packagesService.addPackages(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PackagesDto>> getAll() {
        return ResponseEntity.ok(packagesService.getPackages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackagesDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(packagesService.getPackageById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackagesDto> update(@PathVariable Long id, @RequestBody PackagesDto dto) {
        return ResponseEntity.ok(packagesService.updatePackage(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        packagesService.deletePackage(id);
        return ResponseEntity.noContent().build();
    }
}
