package com.petproject.marketplace.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateProductDTO {
    @Size(min = 4, max = 32)
    private String title;

    @Size(min = 16, max = 1024)
    private String description;

    private int price;
}