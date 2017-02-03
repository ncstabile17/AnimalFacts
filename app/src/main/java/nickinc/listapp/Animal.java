package nickinc.listapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class Animal {

    ArrayList<Fact> facts;
    int size;
    String name;

    public Animal(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public Fact getFact() {
        Random rand = new Random();
        Fact newFact = facts.get(rand.nextInt(facts.size()));
        return newFact;
    }

    public void setFacts(ArrayList<Fact> factsList) {
        facts = new ArrayList<>(factsList);
    }
}
