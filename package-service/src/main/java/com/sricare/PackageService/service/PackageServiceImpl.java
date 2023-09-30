package com.sricare.PackageService.service;

import com.sricare.PackageService.dto.PackageDTO;
import com.sricare.PackageService.model.SriPackage;
import com.sricare.PackageService.model.PackageType;
import com.sricare.PackageService.repository.PackageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PackageServiceImpl implements PackageService{
    private final PackageRepository packageRepository;
    @Override
    public void AddPackage(PackageDTO packageDTO) {
        SriPackage sriPackage=SriPackage.builder()
                .name(packageDTO.getName())
                .price(packageDTO.getPrice())
                .packageType(packageDTO.getPackageType())
                .build();
        packageRepository.save(sriPackage);

    }

    @Override
    public List<SriPackage> getAllPackages() {
        return packageRepository.findAll();
    }

    @Override
    public List<SriPackage> getAllPackagesByType(PackageType type) {
        return packageRepository.getSriPackageByPackageType(type);
    }

    @Override
    public SriPackage getPackageById(Long id) {
        Optional<SriPackage> sriPackage=packageRepository.findById(id);
        return sriPackage.orElse(null);
    }
}
