package com.sricare.PackageService.service;

import com.sricare.PackageService.dto.PackageDTO;
import com.sricare.PackageService.model.SriPackage;
import com.sricare.PackageService.model.PackageType;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PackageService {

    void AddPackage(@RequestBody PackageDTO packageDTO);
    List<SriPackage> getAllPackages();
    List<SriPackage> getAllPackagesByType(PackageType type);

    SriPackage getPackageById(Long id);
}
