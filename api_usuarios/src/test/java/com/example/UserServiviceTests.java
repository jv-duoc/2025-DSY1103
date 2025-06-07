package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.api_usuarios.services.UserService;

@SpringBootTest
class UserServiviceTests {

    @Autowired
    private UserService userService;

    @Test
    void probarHashear(){
        String password = "Hola123";
        String hash = userService.hashearPassword(password);
        boolean coincide = userService.comprobarPassword(hash, password);
        assertEquals(true, coincide);
    }
}
