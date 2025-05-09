package com.example.api_usuarios.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_usuarios.models.entities.User;
import com.example.api_usuarios.models.requests.UserCreate;
import com.example.api_usuarios.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> obtenerTodos() {
        return userRepository.findAll();    
    }

    public User registrar(UserCreate usuario){
        try {
            User nuevoUsuario = new User();
            //cammpos de negocio
            nuevoUsuario.setFechaCreacion(new Date());
            nuevoUsuario.setActivo(true);
            //campos que vienen del cliente
            nuevoUsuario.setNombre(usuario.getNombre());
            nuevoUsuario.setEmail(usuario.getEmail());
            nuevoUsuario.setPassword(usuario.getPassword());
            nuevoUsuario.setTelefono(usuario.getTelefono());

            return userRepository.save(nuevoUsuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario registrado");
        }
    }
}
