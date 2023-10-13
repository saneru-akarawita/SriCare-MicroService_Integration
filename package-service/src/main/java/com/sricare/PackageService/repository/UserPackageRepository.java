package com.sricare.PackageService.repository;

import com.sricare.PackageService.model.SriPackage;
import com.sricare.PackageService.model.UserPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPackageRepository extends JpaRepository<UserPackage,Long> {
}
