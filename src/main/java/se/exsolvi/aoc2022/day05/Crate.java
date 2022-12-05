package se.exsolvi.aoc2022.day05;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Crate {

    @NonNull
    private final Character content;

    public String toString() {
        return "[" + content + "]";
    }
}
