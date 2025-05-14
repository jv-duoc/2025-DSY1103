package com.example.api_autos.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class Vehiculo {
    
    private String id;

    @NotBlank
    private String marca;
    
    @NotBlank
    private String modelo;
    
    @Min(1)
    @Max(99999)
    private int precio;
}
