package com.sricare.PackageService.repository;

import com.sricare.PackageService.model.SriPackage;
import com.sricare.PackageService.model.PackageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<SriPackage,Long> {
    List<SriPackage> getSriPackageByPackageType(PackageType packageType);
}
