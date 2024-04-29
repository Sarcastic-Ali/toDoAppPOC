package com.telus.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerDto {

    private Long id;

    @NotNull
    @NotBlank(message = "This field cannot be empty, only characters allowed")
    private String name;
}
