package se.exsolvi.aoc2022.day03;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class Compartment {
    @NonNull
    private String content;
    public String toString() {
        return "(" + content + ")";
    }
    public String asString() {
        return content;
    }
    public List<Character> asList() {
        return content.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
    }
}
