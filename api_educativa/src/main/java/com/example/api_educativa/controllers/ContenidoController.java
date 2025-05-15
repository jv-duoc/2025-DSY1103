package com.example.api_educativa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_educativa.models.entities.Contenido;
import com.example.api_educativa.models.requests.ContenidoCrear;
import com.example.api_educativa.models.requests.ContenidoModificar;
import com.example.api_educativa.services.ContenidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contenido")
public class ContenidoController {
    @Autowired
    private ContenidoService contenidoService;


    @GetMapping("")
    public List<Contenido> listarTodos(){
        return contenidoService.obtenerTodos();
    }

    @PostMapping("")
    public Contenido crearNuevo(@Valid @RequestBody ContenidoCrear cuerpo){
        return contenidoService.crearNuevo(cuerpo);
    }

    @DeleteMapping("/{id}")
    public String eliminarContenido(@PathVariable int id){
        contenidoService.eliminar(id);
        return "Ok";
    }

    @PutMapping("")
    public Contenido modificar(@RequestBody ContenidoModificar cuerpo){
        return contenidoService.modificar(cuerpo);
    }
}
