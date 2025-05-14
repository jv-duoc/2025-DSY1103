package com.example.api_autos.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.api_autos.models.Vehiculo;

@Repository
public class VehiculoRepository {
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();

    public VehiculoRepository(){
        vehiculos.add(new Vehiculo("1", "Chevrolet", "Sail", 8000));
        vehiculos.add(new Vehiculo("2", "Cherry", "Tiggo", 18000));
        vehiculos.add(new Vehiculo("3", "Subaru", "Impreza", 40000));
        //vehiculos.add(new Vehiculo("3", "Subaru", "Impreza", 40000));
    }
    public ArrayList<Vehiculo> obtenerTodos(){
        return vehiculos;
    }
    public Vehiculo obtenerUno(String id){
        for (Vehiculo v : vehiculos) {
            if(v.getId().equals(id)){
                return v;
            }
        }
        return null;
    }

    

    public void insertar(Vehiculo nuevoVeh){
        vehiculos.add(nuevoVeh);
    }
    public void eliminar(Vehiculo adiosVeh){
        vehiculos.remove(adiosVeh);
    }
}
