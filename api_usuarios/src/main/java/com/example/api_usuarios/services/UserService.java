package com.example.api_usuarios.services;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_usuarios.models.entities.User;
import com.example.api_usuarios.models.requests.UserCreate;
import com.example.api_usuarios.models.requests.UserUpdate;
import com.example.api_usuarios.repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired JwtService jwtService;


    public List<User> obtenerTodos() {
        return userRepository.findAll();    
    }

    public User obtenerPorId(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User obtenerPorEmail(String email){
        return userRepository.findByEmail(email);
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
            nuevoUsuario.setPassword(hashearPassword(usuario.getPassword()));
            nuevoUsuario.setTelefono(usuario.getTelefono());

            return userRepository.save(nuevoUsuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario registrado");
        }
    }

    public String hashearPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password); 
    }

    public boolean comprobarPassword(String hash,String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password,hash); 
    }

    public User actualizar(UserUpdate body) {
        User usuario = userRepository.findById(body.getId()).orElse(null);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        if(body.getNombre() != null) {
            usuario.setNombre(body.getNombre());
        }
        
        if(body.getTelefono() != null) {
            usuario.setTelefono(body.getTelefono());
        }

        if(body.getPassword() != null) {
            usuario.setPassword(hashearPassword(body.getPassword()));
        }

        return userRepository.save(usuario);
    }

    public void eliminar(int id){
        User usuario = userRepository.findById(id).orElse(null);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        userRepository.delete(usuario);
    }

    public String intentarLogin(String email, String password) {
        User user = obtenerPorEmail(email);
        if(user != null){
            boolean passwordCorrecta = comprobarPassword(user.getPassword(),password);

            if(passwordCorrecta){
                return jwtService.generarJwt(user);
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Contraseña invalida");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Correo no registrado");
        }
        
    }

    
}
