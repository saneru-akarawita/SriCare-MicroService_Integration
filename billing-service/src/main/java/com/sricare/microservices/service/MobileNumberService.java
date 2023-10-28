package com.sricare.microservices.service;

import com.sricare.microservices.dto.MobileNumberRequestDto;
import com.sricare.microservices.model.MobileNumbers;
import com.sricare.microservices.repository.MobileNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MobileNumberService {

    private final MobileNumberRepository mobileNumberRepository;

    public void addMobileNumbers(List<MobileNumberRequestDto> mobileNumberRequest) {
        List<MobileNumbers> mobileNumbers = mobileNumberRequest.stream()
                .map(this::mapToDto)
                .toList();

        mobileNumberRepository.saveAll(mobileNumbers);
    }

    private MobileNumbers mapToDto(MobileNumberRequestDto mobileNumberRequestDto) {
        MobileNumbers mobileNumbers = new MobileNumbers();
        mobileNumbers.setMobileNumber(mobileNumberRequestDto.getMobileNumber());
        return mobileNumbers;
    }


    public List<MobileNumberRequestDto> getMobileNumbers() {
        List<MobileNumbers> mobileNumbers = mobileNumberRepository.findAll();
        return mobileNumbers.stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    private MobileNumberRequestDto mapToResponseDto(MobileNumbers mobileNumbers) {
        MobileNumberRequestDto mobileNumberRequestDto = new MobileNumberRequestDto();
        mobileNumberRequestDto.setMobileNumber(mobileNumbers.getMobileNumber());
        return mobileNumberRequestDto;
    }

    public List<MobileNumberRequestDto> getMobileNumbersByMobileNumber(String mobileNumber) {
        List<MobileNumbers> mobileNumbers = mobileNumberRepository.findByMobileNumber(mobileNumber);
        return mobileNumbers.stream()
                .map(this::mapToResponseDto)
                .toList();
    }
}
