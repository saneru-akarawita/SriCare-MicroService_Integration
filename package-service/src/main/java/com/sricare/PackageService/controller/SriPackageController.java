package com.sricare.PackageService.controller;


import com.sricare.PackageService.dto.PackageDTO;
import com.sricare.PackageService.model.PackageType;
import com.sricare.PackageService.model.SriPackage;
import com.sricare.PackageService.service.PackageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/package")
public class SriPackageController {
    private final PackageService packageService;
    @PostMapping("/add")
    public void addPackage(@RequestBody PackageDTO packageDTO){
        packageService.AddPackage(packageDTO);
    }

    @PostMapping("/activate/{user_id}/{package_id}")
    public void activatePachage(@PathVariable long user_id,
            @PathVariable long package_id){
        packageService.activatePackage(user_id,package_id);

    }

    @GetMapping("/all")
    public ResponseEntity<List<SriPackage>> getAllPackages(){
        if(!packageService.getAllPackages().isEmpty()){
            return ResponseEntity.ok(packageService.getAllPackages());
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/all/{type}")
    public  ResponseEntity<List<SriPackage>> getAllPackagesByType(@PathVariable("type")
            PackageType packageType){
        List<SriPackage> packages=
                packageService.getAllPackagesByType(packageType);
        if(!packages.isEmpty()){
            return ResponseEntity.ok(packages);
        }else {
            return ResponseEntity.noContent().build();
        }

    }
    @GetMapping("/{id}")
    public Boolean getPackageById(@PathVariable("id") Long id)
    {
        SriPackage sriPackage = packageService.getPackageById(id);

        return Objects.equals(sriPackage.getId(), id);
    }
}
