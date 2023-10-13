package com.sricare.PackageService.controller;


import com.sricare.PackageService.dto.PackageDTO;
import com.sricare.PackageService.model.PackageType;
import com.sricare.PackageService.model.SriPackage;
import com.sricare.PackageService.service.PackageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/package")
public class SriPackageController {
    private final PackageService packageService;
    @PostMapping("/add")
    public void addPackage(@RequestBody PackageDTO packageDTO){
        packageService.AddPackage(packageDTO);
    }

    @PostMapping("/activate/{user_id}")
    public void activatePachage(@PathVariable long user_id,
            @RequestBody long package_id){
        packageService.activatePackage(user_id,package_id);

    }

    @GetMapping("/all")
    public List<SriPackage> getAllPackages(){
        return packageService.getAllPackages();
    }

    @GetMapping("/all/{type}")
    public List<SriPackage> getAllPackagesByType(@PathVariable("type")
            PackageType packageType){
        return packageService.getAllPackagesByType(packageType);
    }
    @GetMapping("/{id}")
    public Boolean getPackageById(@PathVariable("id") Long id)
    {
        SriPackage sriPackage = packageService.getPackageById(id);

        return Objects.equals(sriPackage.getId(), id);
    }
}
