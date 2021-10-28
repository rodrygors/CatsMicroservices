package com.example.backendshelter.controller;

import com.example.backendshelter.controller.request.Response.FactResponse;
import com.example.backendshelter.model.Pet;
import com.example.backendshelter.controller.request.Create.PetFeedCreateRequest;
import com.example.backendshelter.controller.request.Create.PetCreateRequest;
import com.example.backendshelter.service.FactService;
import com.example.backendshelter.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class AdoptionController {

    private final PetService petService;
    private final FactService factService;

    public AdoptionController(PetService petService, FactService factService) {
        this.petService = petService;
        this.factService = factService;
    }

    @GetMapping(value = "/pets")
    public List<Pet> getPets() {
        return petService.findAll();
    }

    @GetMapping(value = "/pets/{id}")
    public Pet getPet(@PathVariable(value = "id") Long petId) {
        return petService.findById(petId);
    }


    @PostMapping(value = "/pets", consumes = "application/json")
    public List<Pet> addPet(@RequestBody @Valid List<PetCreateRequest> petCreateRequest) {
        return petService.save(petCreateRequest);
    }

    @PostMapping(value = "/pets/feed", consumes = "application/json")
    public Pet addPetFeed(@RequestBody @Valid PetFeedCreateRequest petFeedRQS) {
        return petService.addNewPetFeed(petFeedRQS);
    }

    @GetMapping(value = "/pets/cats/fact")
    public ResponseEntity<FactResponse> getCatFact() {
        final String fact = factService.getFact();
        return ResponseEntity.ok(new FactResponse(fact));
    }

    @GetMapping(value = "/get-fact")
    public String getCarFactString() {
        return factService.getFact();
    }

   /* @PostMapping(value = "/pet", consumes = "application/json")
    public List<Pet> addPet(@RequestBody @Valid List<Pet> pet) throws PetException {
        return petService.save(pet);
    }
*/
    /*//Saving several pets at the same time
    @PostMapping(value = "/pet", consumes = "application/json")
    public List<Pet> addPet(@RequestBody @Valid List<Pet> pet) throws PetException {
        return petService.save(pet);
    }*/
    /*

    @PostMapping(value = "/pet")
    public Pet createPet(@Valid @RequestBody Pet pet) throws PetException {
        return petService.save(pet);
    }
*/


}
