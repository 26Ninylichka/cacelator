package com.example.cacelator.controller.dto;

import com.example.cacelator.model.Status;
import com.example.cacelator.model.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequestDto {

    @Email(message = "Invalid email format")
    private String email;
    private Status status;
    private Type type;
}