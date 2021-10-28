package com.example.backendshelter.controller;

import com.example.backendshelter.controller.request.Create.PetCreateRequest;
import com.example.backendshelter.controller.request.Create.ShelterCreateRequest;
import com.example.backendshelter.controller.request.Response.PetResponseReturn;
import com.example.backendshelter.controller.request.Response.ShelterPetResponseReturn;
import com.example.backendshelter.controller.request.Response.ShelterResponseReturn;
import com.example.backendshelter.model.Pet;
import com.example.backendshelter.model.Shelter;
import com.example.backendshelter.service.PetService;
import com.example.backendshelter.service.ShelterService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShelterController {

    private final ShelterService shelterService;
    private final PetService petService;

    public ShelterController(ShelterService shelterService, PetService petService) {
        this.shelterService = shelterService;
        this.petService = petService;
    }

    @GetMapping("/shelter/{name}")
    public ShelterResponseReturn getShelterByName(String name) {
        Shelter shelter = shelterService.findByNameContaining(name);
        ShelterResponseReturn shelterResponse = new ShelterResponseReturn(
                shelter.getId(),
                shelter.getName(),
                shelter.getCapacity(),
                shelter.getShelterLocation());
        return shelterResponse;
    }

    @GetMapping("/shelter/{id}")
    public ShelterResponseReturn existsById(Long id) {
        Shelter shelter = shelterService.findById(id);
        ShelterResponseReturn shelterResponse = new ShelterResponseReturn(
                shelter.getId(),
                shelter.getName(),
                shelter.getCapacity(),
                shelter.getShelterLocation());
        return shelterResponse;
    }

    @PostMapping(value = "/shelter", consumes = "application/json")
    public ShelterResponseReturn addShelter(@RequestBody ShelterCreateRequest shelterReq) {
        Shelter shelter = Shelter
                .builder()
                .name(shelterReq.getName())
                .capacity(shelterReq.getCapacity())
                .shelterLocation(shelterReq.getShelterLocation())
                .build();
        shelterService.save(shelter);
        ShelterResponseReturn shelterResp = new ShelterResponseReturn();
        shelterResp.setId(shelter.getId());
        shelterResp.setName(shelter.getName());
        shelterResp.setCapacity(shelter.getCapacity());
        shelterResp.setShelterLocation(shelter.getShelterLocation());
        return shelterResp;
    }

    @PostMapping(value = "/shelter/{id}/pets")
    public ShelterPetResponseReturn createPetOnShelter(@RequestBody List<PetCreateRequest> petList, Long id) {
        List<Pet> pets = new ArrayList<>();
        for (PetCreateRequest petReq : petList) {
            pets.add(Pet
                    .builder()
                    .petType(petReq.getPetType())
                    .name(petReq.getName())
                    .build());
        }
        Shelter shelter = petService.saveinshelter(pets, id);
        List<PetResponseReturn> petResps = new ArrayList<>();
        ShelterPetResponseReturn shelterPetResponseReturn =
                new ShelterPetResponseReturn(shelter.getId(), shelter.getName()
                        , shelter.getCapacity(), shelter.getShelterLocation(), petResps);
        for (Pet pet : shelter.getPets()) {
            PetResponseReturn petResponseReturn = new PetResponseReturn(
                    pet.getId(),
                    pet.getPetType(),
                    pet.getName());
            shelterPetResponseReturn.getPetResponseReturnList().add(petResponseReturn);
        }
        return shelterPetResponseReturn;
    }

    @DeleteMapping(value = "/shelter/{id}")
    public void deleteDriver(Long id) {shelterService.deleteById(id);}

}
