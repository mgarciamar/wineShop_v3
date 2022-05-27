package com.example.wineshop;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class TypeModelAssembler implements RepresentationModelAssembler<Type, EntityModel<Type>> {

    @Override
    public EntityModel<Type> toModel(Type type) {

        return EntityModel.of(type, //
                linkTo(methodOn(TypeController.class).one(type.getId())).withSelfRel(),
                linkTo(methodOn(TypeController.class).all()).withRel("type"));
    }
}
