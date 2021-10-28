package com.example.backendshelter.controller.request.Response;

import com.example.backendshelter.model.ShelterLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShelterResponseReturn {

    private Long id;

    private String name;
    private int capacity;
    private ShelterLocation shelterLocation;
}

