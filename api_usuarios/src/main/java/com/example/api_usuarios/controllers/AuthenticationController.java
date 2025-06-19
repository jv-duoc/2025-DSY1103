package com.example.api_usuarios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_usuarios.models.entities.User;
import com.example.api_usuarios.models.requests.LoginRequest;
import com.example.api_usuarios.models.responses.LoginResponse;
import com.example.api_usuarios.services.JwtService;
import com.example.api_usuarios.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Parameter;



@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;
    
    @PostMapping("token")
    public LoginResponse postMethodName(@RequestBody @Valid LoginRequest body) {

        String token = userService.intentarLogin(body.getEmail(), body.getPassword());

        return new LoginResponse(token);
    }

    @GetMapping("yo")
    @SecurityRequirement(name = "bearerAuth")
    public User misDatos(@Parameter(hidden = true) @RequestHeader("Authorization") String authHeader) {
        User user = jwtService.comprobarToken(authHeader);
        return user;
    }
    
}
