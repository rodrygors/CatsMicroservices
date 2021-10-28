package com.bootcamp.catninja.controller;

import com.bootcamp.catninja.service.FactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FactController {

    final private FactService factService;

    public FactController(FactService factService) {
        this.factService = factService;
    }

    @GetMapping("/fact")
    public String getFact() {
        final var fact = factService.getFact();
        return fact;
    }

}
