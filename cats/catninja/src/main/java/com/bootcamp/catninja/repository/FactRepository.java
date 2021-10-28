package com.bootcamp.catninja.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FactRepository {

    private List<String> facts = new ArrayList<>();

    public FactRepository() {
        facts.add("Cats can jump up to 7 times their tail length.");
        facts.add("Cats can predict earthquakes. We humans are not 100% sure how they do it. There are several different theories.");
        facts.add("The chlorine in fresh tap water irritates sensitive parts of the cat's nose. Let tap water sit for 24 hours before giving it to a cat.");
        facts.add("A cat has two vocal chords, and can make over 100 sounds.");
        facts.add("British cat owners spend roughly 550 million pounds yearly on cat food.");
    }

    public String getFactById(int id) {
        System.out.println(id);
        return facts.get(id);
    }

    public int getFactCount() {
        return facts.size();
    }
}
