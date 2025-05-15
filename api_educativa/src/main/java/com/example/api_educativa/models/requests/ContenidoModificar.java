package com.example.api_educativa.models.requests;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContenidoModificar {

    private int id;

    private String titulo;
    
    private String descripcion;

    @URL
    private String urlVideo;
}
