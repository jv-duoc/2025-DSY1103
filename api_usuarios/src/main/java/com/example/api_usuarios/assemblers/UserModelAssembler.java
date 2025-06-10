package com.example.api_usuarios.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.api_usuarios.controllers.UserControllerV2;
import com.example.api_usuarios.models.entities.User;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
            linkTo(methodOn(UserControllerV2.class).obtenerUno(user.getId())).withSelfRel(),
            linkTo(methodOn(UserControllerV2.class).obtenerTodos()).withRel("usuarios")
        );
    }
}
