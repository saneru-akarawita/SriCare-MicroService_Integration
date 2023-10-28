package com.sricare.microservices.controller;

import com.sricare.microservices.dto.AuthenticationRequestDto;
import com.sricare.microservices.dto.AuthenticationResponseDto;
import com.sricare.microservices.dto.RegisterRequestDto;
import com.sricare.microservices.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @RequestMapping("/register")
    public AuthenticationResponseDto register(
            @RequestBody RegisterRequestDto registerRequestDto
    ) {
        return authenticationService.register(registerRequestDto);
    }

    @RequestMapping("/authenticate")
    public AuthenticationResponseDto authenticate(
            @RequestBody AuthenticationRequestDto authenticationRequestDto
            ) {
        return authenticationService.authenticate(authenticationRequestDto);
    }
}
