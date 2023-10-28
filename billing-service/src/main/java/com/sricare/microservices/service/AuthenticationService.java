package com.sricare.microservices.service;

import com.sricare.microservices.dto.AuthenticationRequestDto;
import com.sricare.microservices.dto.AuthenticationResponseDto;
import com.sricare.microservices.dto.RegisterRequestDto;
import com.sricare.microservices.model.Role;
import com.sricare.microservices.model.User;
import com.sricare.microservices.repository.MobileNumberRepository;
import com.sricare.microservices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final MobileNumberRepository mobileNumberRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterRequestDto registerRequestDto) {
        if(!registerRequestDto.getPassword().equals(registerRequestDto.getConfirmPassword())) {
            return AuthenticationResponseDto.builder()
                    .errors(List.of("Password and Confirm Password do not match"))
                    .build();
        }

        if(mobileNumberRepository.existsByMobileNumber(registerRequestDto.getMobileNumber())) {

            var user = User.builder()
                    .firstName(registerRequestDto.getFirstName())
                    .lastName(registerRequestDto.getLastName())
                    .email(registerRequestDto.getEmail())
                    .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                    .role(Role.ROLE_USER)
                    .mobileNumber(registerRequestDto.getMobileNumber())
                    .build();

            userRepository.save(user);

            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponseDto.builder()
                    .token(jwtToken)
                    .build();

        }else{
            return AuthenticationResponseDto.builder()
                    .errors(List.of("Invalid Mobile Number"))
                    .build();
        }

    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    authenticationRequestDto.getEmail(),
                    authenticationRequestDto.getPassword()
            )
        );

        var user = userRepository.findByEmail(authenticationRequestDto.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}
