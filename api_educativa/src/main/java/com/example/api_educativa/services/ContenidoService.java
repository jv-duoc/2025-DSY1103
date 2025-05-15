package com.example.api_educativa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_educativa.models.entities.Contenido;
import com.example.api_educativa.models.entities.Curso;
import com.example.api_educativa.models.requests.ContenidoCrear;
import com.example.api_educativa.models.requests.ContenidoModificar;
import com.example.api_educativa.repositories.ContenidoRepository;
import com.example.api_educativa.repositories.CursoRepository;

@Service
public class ContenidoService {
    
    @Autowired
    private ContenidoRepository contenidoRepository;
    @Autowired
    private CursoRepository cursoRepository;


    public List<Contenido> obtenerTodos(){
        return contenidoRepository.findAll();
    }

    public Contenido obtenerPorId(int id){
        Contenido contenido =  contenidoRepository.findById(id).orElse(null);
        if(contenido == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Contenido no encontrado");
        }
        return contenido;
    }

    public Contenido crearNuevo(ContenidoCrear solicitud){
        Contenido nuevo = new Contenido();

        nuevo.setDescripcion(solicitud.getDescripcion());
        nuevo.setTitulo(solicitud.getTitulo());
        nuevo.setUrlVideo(solicitud.getUrlVideo());
        
        Curso cursoAsociado = cursoRepository.findById(solicitud.getIdCurso()).orElse(null);

        nuevo.setCurso(cursoAsociado);
        return contenidoRepository.save(nuevo);
    }

    public void eliminar(int id){
        Contenido contenido = obtenerPorId(id);
        contenidoRepository.delete(contenido);
    }

    public Contenido modificar(ContenidoModificar solicitud){
        Contenido contenido = obtenerPorId(solicitud.getId());

        if(solicitud.getTitulo() != null){
            contenido.setTitulo(solicitud.getTitulo());
        }
        if(solicitud.getDescripcion() != null){
            contenido.setDescripcion(solicitud.getDescripcion());
        }
        if(solicitud.getUrlVideo() != null){
            contenido.setUrlVideo(solicitud.getUrlVideo());
        }

        return contenidoRepository.save(contenido);
    }
}
