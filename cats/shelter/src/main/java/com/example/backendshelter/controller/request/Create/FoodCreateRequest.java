package com.example.backendshelter.controller.request.Create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FoodCreateRequest {
    private String brand;
    private String description;
}
