package com.example.backendshelter.controller.request.Create;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetFeedCreateRequest {
    private long petId;
    private long feedId;
}