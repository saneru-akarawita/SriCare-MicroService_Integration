package com.sricare.microservices.dto;

import com.sricare.microservices.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponseDto {
    private String token;
    private List<String> errors;
    private User user;
    private boolean result;
}
