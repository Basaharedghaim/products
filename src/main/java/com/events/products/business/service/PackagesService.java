package com.events.products.business.service;

import com.events.products.dto.PackagesDto;
import com.events.products.dto.ProductDto;
import com.events.products.repository.PackagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PackagesService {
    private final PackagesRepository packagesRepository;
    public List<PackagesDto> getProducts() {
        return null;
    }

    public void deletePackage(Long id) {
    }

    public PackagesDto updatePackage(Long id, PackagesDto productDto) {
        return null;

    }

    public List<PackagesDto> getPackages() {
        return null;

    }

    public PackagesDto getPackageById(Long id) {
        return null;

    }

    public PackagesDto addPackages(PackagesDto packagesDto) {
        return null;

    }
}
