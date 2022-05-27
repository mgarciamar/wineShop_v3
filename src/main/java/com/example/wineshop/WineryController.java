package com.example.wineshop;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class WineryController {

    private final WineryRepository repository;
    private final WineryModelAssembler assembler;

    WineryController(WineryRepository repository, WineryModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }



    @GetMapping("/winery")
    CollectionModel<EntityModel<Winery>> all() {

        List<EntityModel<Winery>> winery = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(winery, linkTo(methodOn(WineryController.class).all()).withSelfRel());
    }
    @PostMapping("/winery")
    ResponseEntity<?> newWinery(@RequestBody Winery newWinery) {

        EntityModel<Winery> entityModel = assembler.toModel(repository.save(newWinery));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    // Single item

    @GetMapping("/winery/{id}")
    EntityModel<Winery> one(@PathVariable Long id) {

        Winery winery = repository.findById(id) //
                .orElseThrow(() -> new WineryNotFoundException(id));

        return assembler.toModel(winery);
    }

    @PutMapping("/winery/{id}")
    ResponseEntity<?> replaceWinery(@RequestBody Winery newWinery, @PathVariable Long id) {

        Winery updatedWinery = repository.findById(id) //
                .map(winery -> {
                    winery.setName(newWinery.getName());
                    return repository.save(winery);
                }) //
                .orElseGet(() -> {
                    newWinery.setId(id);
                    return repository.save(newWinery);
                });

        EntityModel<Winery> entityModel = assembler.toModel(updatedWinery);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/winery/{id}")
    ResponseEntity<?> deleteWinery(@PathVariable Long id) {

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}