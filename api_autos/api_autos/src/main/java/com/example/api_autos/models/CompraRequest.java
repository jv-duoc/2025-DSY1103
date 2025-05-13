package com.example.api_autos.models;

import lombok.Data;

@Data
public class CompraRequest {
    private String idVehiculo;
    private String emailCliente;
    private String numeroTarjeta;
}
