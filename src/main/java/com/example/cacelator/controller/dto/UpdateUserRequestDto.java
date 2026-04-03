package com.example.cacelator.controller.dto;
import com.example.cacelator.service.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequestDto {

    private String name;

    private String email;

    private String phoneNumber;

    private Status status;
}