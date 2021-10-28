package com.example.backendshelter.controller.request.Create;

import com.example.backendshelter.model.ShelterLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShelterCreateRequest {

    private String name;
    private int capacity;
    private ShelterLocation shelterLocation;
}
