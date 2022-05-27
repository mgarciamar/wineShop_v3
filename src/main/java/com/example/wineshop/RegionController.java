package com.example.wineshop;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RegionController {


    private final RegionRepository repository;
    private final RegionModelAssembler assembler;


    RegionController(RegionRepository repository, RegionModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/region")
    CollectionModel<EntityModel<Region>> all() {

        List<EntityModel<Region>> regions = repository.findAll().stream()
                .map(region -> EntityModel.of(region,
                        linkTo(methodOn(RegionController.class).one(region.getId())).withSelfRel(),
                        linkTo(methodOn(WineController.class).all()).withRel("region")))
                .collect(Collectors.toList());

        return CollectionModel.of(regions, linkTo(methodOn(WineController.class).all()).withSelfRel());
    }

    @PostMapping("/region")
    ResponseEntity<?> newRegion(@RequestBody Region newRegion) {

        EntityModel<Region> entityModel = assembler.toModel(repository.save(newRegion));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/region/{id}")
    EntityModel<Region> one(@PathVariable Long id) {

        Region region = repository.findById(id)
                .orElseThrow(() -> new RegionNotFoundException(id));

        return EntityModel.of(region, //
                linkTo(methodOn(RegionController.class).one(id)).withSelfRel(),
                linkTo(methodOn(RegionController.class).all()).withRel("region"));
    }

    @PutMapping("/region/{id}")
    ResponseEntity<?> replaceRegion(@RequestBody Region newRegion, @PathVariable Long id){
        Region updatedRegion = repository.findById(id) //
                .map(region -> {
                    region.setCountry(newRegion.getCountry());
                    region.setName(newRegion.getName());
                    return repository.save(region);
                })//
                .orElseGet(() -> {
                    newRegion.setId(id);
                    return repository.save(newRegion);
                });
        EntityModel<Region> entityModel = assembler.toModel(updatedRegion);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/region/{id}")
    ResponseEntity<?> deleteRegion(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }




}
