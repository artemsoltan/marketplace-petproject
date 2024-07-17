package com.petproject.marketplace.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    @Size(min = 4, max = 64)
    private String name;

    @Size(min = 4, max = 64)
    private String surname;

    @Size(min = 4, max = 64)
    private String username;

    @Size(min = 4, max = 64)
    private String password;
}