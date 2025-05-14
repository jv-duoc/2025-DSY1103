package com.example.api_autos.services;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_autos.models.ModificarVehiculo;
import com.example.api_autos.models.Vehiculo;
import com.example.api_autos.repository.VehiculoRepository;

@Service
public class VehiculoService {
    
    @Autowired
    private VehiculoRepository repo;

    public ArrayList<Vehiculo> obtenerTodos(){
        return repo.obtenerTodos();
    }

    public Vehiculo obtenerUno(String id){
        Vehiculo v = repo.obtenerUno(id);
        if(v == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return v;
    }

    public void agregar(Vehiculo vehiculo){
        vehiculo.setId(UUID.randomUUID().toString());
        repo.insertar(vehiculo);
    }

    public void eliminar(String id){
        Vehiculo v = obtenerUno(id);
        repo.eliminar(v);
    }

    public void modificar(String id, ModificarVehiculo modificarVehiculo){
        Vehiculo v = obtenerUno(id);
        v.setPrecio(modificarVehiculo.getPrecio());
    }
}
