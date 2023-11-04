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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {

    private final MobileNumberRepository mobileNumberRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final WebClient.Builder webClientBuilder;

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

        // Get all packages from package service
//        boolean result = Boolean.TRUE.equals(webClientBuilder.build() .get()
//                .uri("http://package-service/api/v1/package/1")
//                .retrieve()
//                .bodyToMono(Boolean.class)
//                .block());

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .user(user)
                .build();
    }
}
