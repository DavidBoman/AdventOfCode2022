package se.exsolvi.aoc2022.day03;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuckSack {

    @NonNull
    private final Compartment firstCompartment;
    @NonNull
    private final Compartment secondCompartment;
    public RuckSack(String content) {
        firstCompartment = new Compartment(content.substring(0, content.length()/2));
        secondCompartment = new Compartment(content.substring(content.length()/2, content.length()));
    }

    public String getUniqCommonAsString() {
        List<Character> common = new ArrayList<>(firstCompartment.asList());
        common.retainAll(secondCompartment.asList());
        return common.stream().map(Object::toString).distinct().collect(Collectors.joining());
    }

    public List<Character> asList() {
        List<Character> all = firstCompartment.asList();
        all.addAll(secondCompartment.asList());
        return all;
    }

    public String toString() {
        return "[" + firstCompartment + "," + secondCompartment + "]";
    }

}
