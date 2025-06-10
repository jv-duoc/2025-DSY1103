package com.example.api_usuarios.models.entities;


import java.util.Date;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @Column(nullable = false,unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    private String telefono;

    @Column(name="fecha_creacion")
    private Date fechaCreacion;

    private Boolean activo;
    
}
