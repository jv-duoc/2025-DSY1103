package com.example.api_educativa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_educativa.models.entities.Curso;
import com.example.api_educativa.models.requests.CursoCrear;
import com.example.api_educativa.services.CursoService;

import jakarta.validation.Valid;

@RequestMapping("/curso") // url:puerto/curso
@RestController
public class CursoController {
    
    @Autowired
    private CursoService cursoService;

    @GetMapping("")
    public List<Curso> todos(){
        return cursoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Curso listarUno(@PathVariable int id){
        return cursoService.obtenerPorId(id);
    }


    @PostMapping("")
    public Curso crearNuevo(@Valid @RequestBody CursoCrear cuerpo){
        return cursoService.crearNuevo(cuerpo);
    }
}
