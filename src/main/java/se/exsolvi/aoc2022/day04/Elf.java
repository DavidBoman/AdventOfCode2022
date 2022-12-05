package se.exsolvi.aoc2022.day04;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Elf {
    @Getter
    @NonNull
    private final List<Integer> sections;
    public Elf(String sectionString) {
        String[] indexes = sectionString.split("-");
        sections = IntStream.rangeClosed(Integer.parseInt(indexes[0]), Integer.parseInt(indexes[1])).boxed().toList();
    }

    public Boolean containsCommonSections(Elf elf) {
        List<Integer> overlap = new ArrayList<>(sections);
        overlap.removeAll(elf.getSections());
        return overlap.size() < sections.size();
    }

    public Boolean containsAllSections(Elf elf) {
        List<Integer> overlap = new ArrayList<>(elf.getSections());
        overlap.removeAll(sections);
        return overlap.isEmpty();
    }

}
