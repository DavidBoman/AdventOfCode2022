package se.exsolvi.aoc2022.day04;

import lombok.Getter;
import lombok.NonNull;

import java.util.List;
public record ElfPair(@Getter @NonNull Elf elf0, @Getter @NonNull Elf elf1) {

    public Boolean hasOverlappingSections() {
        return elf0.containsCommonSections(elf1);
    }

    public Boolean hasFullyOverlappingSections() {
        boolean result = elf0.containsAllSections(elf1) || elf1.containsAllSections(elf0);
        return result;
    }

}