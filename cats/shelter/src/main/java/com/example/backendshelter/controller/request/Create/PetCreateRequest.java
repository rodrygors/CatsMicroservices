package com.example.backendshelter.controller.request.Create;

import com.example.backendshelter.model.PetType;
import lombok.*;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetCreateRequest {
    private PetType petType;
    @NotBlank(message = "Name is a mandatory field.")
    private String name;
}
