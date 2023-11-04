package com.sricare.microservices.controller;

import com.sricare.microservices.dto.AuthenticationRequestDto;
import com.sricare.microservices.dto.AuthenticationResponseDto;
import com.sricare.microservices.dto.ChangePasswordRequestDto;
import com.sricare.microservices.dto.RegisterRequestDto;
import com.sricare.microservices.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/changePassword")
    public String changePassword(
            @RequestBody ChangePasswordRequestDto changePasswordRequestDto
            ) {
        return authenticationService.changePassword(changePasswordRequestDto);
    }

    @RequestMapping("/getUserById/{id}")
    public Object getUserById(
            @PathVariable String id) {
        return authenticationService.getUserById(Long.valueOf(id));
    }
}
