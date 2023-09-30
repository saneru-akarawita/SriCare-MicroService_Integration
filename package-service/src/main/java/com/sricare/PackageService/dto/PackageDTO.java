package com.sricare.PackageService.dto;


import com.sricare.PackageService.model.PackageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageDTO {

    private String name;
    private Double price;
    private PackageType PackageType;
}
