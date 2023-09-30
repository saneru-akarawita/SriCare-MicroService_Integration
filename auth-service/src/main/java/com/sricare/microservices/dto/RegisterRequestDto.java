package com.sricare.microservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDto {
    private String email;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String mobileNumber;
}
