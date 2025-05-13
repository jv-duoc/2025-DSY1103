package com.example.api_usuarios.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_usuarios.models.entities.User;
import com.example.api_usuarios.models.requests.UserCreate;
import com.example.api_usuarios.models.requests.UserUpdate;
import com.example.api_usuarios.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> obtenerTodos() {
        return userService.obtenerTodos();
    }

    @PostMapping("/")
    public User registrar(@Valid @RequestBody UserCreate body) {
        return userService.registrar(body);
    }

    @PutMapping()
    public User actualizar(@Valid @RequestBody UserUpdate body) {
        return userService.actualizar(body);
    }
    
}
