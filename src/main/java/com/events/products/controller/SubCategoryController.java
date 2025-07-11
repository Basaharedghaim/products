package com.events.products.controller;

import com.events.products.business.service.SubCategoryService;
import com.events.products.data.dto.SubCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategory/api/v1")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping("/add")
    public ResponseEntity<SubCategoryDto> add(@RequestBody SubCategoryDto dto) {
        return ResponseEntity.ok(subCategoryService.addSubCategory(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubCategoryDto> update(@PathVariable Long id, @RequestBody SubCategoryDto dto) {
        return ResponseEntity.ok(subCategoryService.updateSubCategory(id, dto));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<SubCategoryDto>> getAll() {
        return ResponseEntity.ok(subCategoryService.getAll());
    }

    @GetMapping("by-id/{id}")
    public ResponseEntity<SubCategoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(subCategoryService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{sub-category-id}/item/{item-id}")
    public ResponseEntity<String> addItemToSubCategory(
            @PathVariable(name = "sub-category-id") Long subCategoryId,
            @PathVariable(name = "item-id") Long itemId) {

        subCategoryService.addSubCategoryToStore(subCategoryId, itemId);
        return ResponseEntity.ok("Item added to SubCategory successfully");
    }
}
