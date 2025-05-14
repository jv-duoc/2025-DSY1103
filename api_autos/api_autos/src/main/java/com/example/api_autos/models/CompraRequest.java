package com.example.api_autos.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompraRequest {
    @NotBlank
    private String idVehiculo;
    
    private int idUsuario;

    @NotBlank
    private String numeroTarjeta;
}
