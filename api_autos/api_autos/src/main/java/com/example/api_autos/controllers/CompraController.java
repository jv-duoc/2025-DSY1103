package com.example.api_autos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.api_autos.models.CompraRequest;
import com.example.api_autos.models.CompraResponse;
import com.example.api_autos.models.User;
import com.example.api_autos.models.Vehiculo;
import com.example.api_autos.services.VehiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("compra")
public class CompraController {
    
    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private WebClient webClient;


    @PostMapping("/comprar")
    public CompraResponse comprar(@Valid @RequestBody CompraRequest compraRequest) {
        
        CompraResponse response = new CompraResponse();

        try {
            Vehiculo veh = vehiculoService.obtenerUno(compraRequest.getIdVehiculo());
            if(veh == null){throw new Exception("Vehiculo no encontrado");}

            User usuario = webClient
                .get()
                .uri("http://localhost:8080/usuario/" + compraRequest.getIdUsuario())
                .retrieve()
                .bodyToMono(User.class)
                .block();

            if(usuario == null){throw new Exception("Usuario no encontrado");}

            vehiculoService.eliminar(compraRequest.getIdVehiculo());
            response.setIdBoleta("Compra exitosa vehiculo id" + veh.getId()+' Correo usuario:'+usuario.getEmail());
            response.setExito(true);
        } catch (Exception e) {
            response.setExito(false);
            response.setMensaje("Error al procesar la compra: " + e.getMessage());
            return response;
        }
        
        return response;

    }
}
