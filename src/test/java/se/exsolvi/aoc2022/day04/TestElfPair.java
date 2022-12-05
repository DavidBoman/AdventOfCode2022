package se.exsolvi.aoc2022.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestElfPair {

    @Test
    public void testHasOverlappingSections() {
        Elf elf0;
        Elf elf1;
        ElfPair elfPair;

        elf0 = new Elf("1-6");
        elf1 = new Elf("2-3");
        elfPair = new ElfPair(elf0, elf1);
        Assertions.assertTrue(elfPair.hasOverlappingSections());

        elf0 = new Elf("1-6");
        elf1 = new Elf("1-6");
        elfPair = new ElfPair(elf0, elf1);
        Assertions.assertTrue(elfPair.hasOverlappingSections());

        elf0 = new Elf("1-6");
        elf1 = new Elf("1-7");
        elfPair = new ElfPair(elf0, elf1);
        Assertions.assertTrue(elfPair.hasOverlappingSections());

        elf0 = new Elf("1-6");
        elf1 = new Elf("23-29");
        elfPair = new ElfPair(elf0, elf1);
        Assertions.assertFalse(elfPair.hasOverlappingSections());
    }
    @Test
    public void testHasFullyOverlappingSections() {
        Elf elf0;
        Elf elf1;
        ElfPair elfPair;

        elf0 = new Elf("1-6");
        elf1 = new Elf("2-3");
        elfPair = new ElfPair(elf0, elf1);
        Assertions.assertTrue(elfPair.hasFullyOverlappingSections());

        elf0 = new Elf("1-6");
        elf1 = new Elf("1-6");
        elfPair = new ElfPair(elf0, elf1);
        Assertions.assertTrue(elfPair.hasFullyOverlappingSections());

        elf0 = new Elf("1-6");
        elf1 = new Elf("1-7");
        elfPair = new ElfPair(elf0, elf1);
        Assertions.assertTrue(elfPair.hasOverlappingSections());

        elf0 = new Elf("1-6");
        elf1 = new Elf("23-29");
        elfPair = new ElfPair(elf0, elf1);
        Assertions.assertFalse(elfPair.hasFullyOverlappingSections());

    }
}