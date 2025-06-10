package com.example.api_usuarios.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_usuarios.assemblers.UserModelAssembler;
import com.example.api_usuarios.models.entities.User;
import com.example.api_usuarios.models.requests.UserCreate;
import com.example.api_usuarios.models.requests.UserUpdate;
import com.example.api_usuarios.services.UserService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import jakarta.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping("v2/usuario")
public class UserControllerV2 {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserModelAssembler assembler;
    

    @GetMapping("/")
    public CollectionModel<EntityModel<User>> obtenerTodos() {
        List<EntityModel<User>> usuarios = userService.obtenerTodos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
            linkTo(methodOn(UserControllerV2.class).obtenerTodos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<User> obtenerUno(@PathVariable int id) {
        User user = userService.obtenerPorId(id);
        return assembler.toModel(user);
    }

    @PostMapping("/")
    public EntityModel<User> registrar(@Valid @RequestBody UserCreate body) {
        User user = userService.registrar(body);
        return assembler.toModel(user);
    }

    @PutMapping("/")
    public EntityModel<User> actualizar(@Valid @RequestBody UserUpdate body) {
        User user = userService.actualizar(body);
        return assembler.toModel(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        userService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
