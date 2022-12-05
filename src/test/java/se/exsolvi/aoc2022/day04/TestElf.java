package se.exsolvi.aoc2022.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestElf {
    @Test
    public void testFullyContains() {
        Elf elf0;
        Elf elf1;

        elf0 = new Elf("1-6");
        elf1 = new Elf("2-3");
        Assertions.assertTrue(elf0.containsAllSections(elf1));

        elf0 = new Elf("1-6");
        elf1 = new Elf("1-6");
        Assertions.assertTrue(elf0.containsAllSections(elf1));

        elf0 = new Elf("1-6");
        elf1 = new Elf("1-7");
        Assertions.assertFalse(elf0.containsAllSections(elf1));

        elf0 = new Elf("1-6");
        elf1 = new Elf("23-29");
        Assertions.assertFalse(elf0.containsAllSections(elf1));
    }
    @Test
    public void testContainsCommonSections() {
        Elf elf0;
        Elf elf1;

        elf0 = new Elf("1-6");
        elf1 = new Elf("2-3");
        Assertions.assertTrue(elf0.containsCommonSections(elf1));

        elf0 = new Elf("1-6");
        elf1 = new Elf("1-6");
        Assertions.assertTrue(elf0.containsCommonSections(elf1));

        elf0 = new Elf("1-6");
        elf1 = new Elf("1-7");
        Assertions.assertTrue(elf0.containsCommonSections(elf1));

        elf0 = new Elf("1-6");
        elf1 = new Elf("23-29");
        Assertions.assertFalse(elf0.containsCommonSections(elf1));
    }
}