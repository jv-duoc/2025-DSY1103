package com.example.api_usuarios.models.requests;

import lombok.Data;

@Data
public class UserUpdate {
    private int id;

    private String nombre;
    private String telefono;
    private String password;
}
