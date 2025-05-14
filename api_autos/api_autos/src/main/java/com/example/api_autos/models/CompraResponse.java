package com.example.api_autos.models;

import lombok.Data;

@Data
public class CompraResponse {
    private String idBoleta;
    private String mensaje;
    private Boolean exito;
}
