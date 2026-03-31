package com.example.cacelator.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
    private String displayName;
    private String phoneNumber;
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password must not be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
