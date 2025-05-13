package com.example.api_autos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_autos.services.VehiculoService;

@RestController
@RequestMapping("compra")
public class CompraController {
    
    @Autowired
    private VehiculoService vehiculoService;

    
}
