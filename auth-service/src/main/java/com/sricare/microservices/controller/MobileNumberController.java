package com.sricare.microservices.controller;

import com.sricare.microservices.dto.MobileNumberRequestDto;
import com.sricare.microservices.service.MobileNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mobile")
@RequiredArgsConstructor
public class MobileNumberController {

    private final MobileNumberService mobileNumberService;

    @PostMapping("/add-mobile-numbers")
    @ResponseStatus(HttpStatus.CREATED)
    public String addMobileNumbers(@RequestBody List<MobileNumberRequestDto> mobileNumberRequest){
        mobileNumberService.addMobileNumbers(mobileNumberRequest);
        return "Mobile numbers added successfully";
    }

    @GetMapping("/get-mobile-numbers")
    @ResponseStatus(HttpStatus.OK)
    public List<MobileNumberRequestDto> getMobileNumbers(){
        return mobileNumberService.getMobileNumbers();
    }

    @GetMapping("/get-mobile-number/{mobileNumber}")
    @ResponseStatus(HttpStatus.OK)
    public List<MobileNumberRequestDto> getMobileNumbersByMobileNumber(@PathVariable String mobileNumber){
        return mobileNumberService.getMobileNumbersByMobileNumber(mobileNumber);
    }
}
