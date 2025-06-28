package com.events.products.business.service;

import com.events.products.data.dto.PackagesDto;
import com.events.products.data.entity.PackagesEntity;
import com.events.products.data.repository.PackagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PackagesService {
    private final PackagesRepository packagesRepository;


    public PackagesDto addPackages(PackagesDto dto) {
        PackagesEntity entity = PackagesEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        return mapToDto(packagesRepository.save(entity));
    }

    public void deletePackage(Long id) {
        packagesRepository.deleteById(id);
    }

    public PackagesDto updatePackage(Long id, PackagesDto dto) {
        PackagesEntity entity = packagesRepository.findById(id).orElseThrow();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return mapToDto(packagesRepository.save(entity));
    }

    public List<PackagesDto> getPackages() {
        return packagesRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public PackagesDto getPackageById(Long id) {
        return packagesRepository.findById(id).map(this::mapToDto).orElse(null);
    }

    private PackagesDto mapToDto(PackagesEntity entity) {
        return PackagesDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
