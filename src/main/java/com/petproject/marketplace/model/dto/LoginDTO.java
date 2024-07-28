package com.petproject.marketplace.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginDTO {
    @Size(min = 4, max = 64)
    private String username;

    @Size(min = 4, max = 64)
    private String password;
}