package com.example.cacelator.controller.dto;

import com.example.cacelator.service.model.Status;
import com.example.cacelator.service.model.Type;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;

@Getter
@Setter
public class UpdateUserRequestDto {

    @Email(message = "Invalid email format")
    private String email;
    private Status status;
    private Type type;
}