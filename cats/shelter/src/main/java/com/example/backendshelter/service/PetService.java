package com.example.backendshelter.service;

import com.example.backendshelter.exception.PetNotFound;
import com.example.backendshelter.exception.ShelterNotFound;
import com.example.backendshelter.model.Pet;
import com.example.backendshelter.model.Shelter;
import com.example.backendshelter.repository.PetRepository;
import com.example.backendshelter.controller.request.Create.PetFeedCreateRequest;
import com.example.backendshelter.controller.request.Create.PetCreateRequest;
import com.example.backendshelter.repository.ShelterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final ShelterRepository shelterRepository;

    public PetService(PetRepository petRepository, ShelterRepository shelterRepository) {
        this.petRepository = petRepository;
        this.shelterRepository = shelterRepository;
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }


    public List<Pet> save(List<PetCreateRequest> petCreateRequestList) {
        List<Pet> newPetList = new ArrayList<>();
        Pet newPet;
        for (PetCreateRequest petCreateRequest : petCreateRequestList) {
            newPet = Pet.builder().petType(petCreateRequest.getPetType()).name(petCreateRequest.getName()).build();
            petRepository.save(newPet);
            newPetList.add(newPet);
        }
        return newPetList;
    }

    public Pet findById(Long id) {

        return petRepository.findById(id).orElseThrow(() -> new PetNotFound("Pet doesn't exists."));
    }
    public Pet addNewPetFeed(PetFeedCreateRequest createPetFeedRQ) {

        return null;
    }
//    //Save several Pets at the same time
//    public List<Pet> save(List<Pet> petList)  {
//        List<Pet> newPetList = new ArrayList<>();
//        for (Pet pet : petList) {
//            newPetList.add(this.petRepository.save(pet));
//        }
//        return newPetList;
//    }
//    public Pet save(Pet pet) throws PetException {
//        if (this.checkIfPetIsOnShelter(pet)) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST, "Pet is already on database.");
//        }
//        return this.petRepository.save(pet);
//    }

    public boolean checkIfPetIsOnShelter(Pet pet) {
        if (pet.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pet not found in the refugee.");
        }
        return this.petRepository.existsById(pet.getId());
    }

    public Shelter saveinshelter(List<Pet> pets, Long id) {
        List<Pet> newPetList = new ArrayList<>();
        Optional<Shelter> shelterOptional = shelterRepository.findById(id);
        if (shelterOptional.isPresent()) {
            Shelter shelter = shelterOptional.get();
            for (Pet pet : pets) {
                pet.setShelter(shelter);
                newPetList.add(this.petRepository.save(pet));
            }
            return shelter;
        }
        return null;
    }
}
