package se.exsolvi.aoc2022.day04;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.exsolvi.aoc2022.day03.RuckSack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day04 {
    private final String input = "input/day04/input.txt";
    private final String test = "input/day04/test.txt";
    private List<RuckSack> ruckSackList;

    public static void main(String[] args) {
        try {
            new Day04().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
     public void run(String... args) throws Exception {

        Path path = Paths.get(input);
         try (Stream<String> stream = Files.lines(path)) {
             long duplicateSectionsCount = stream.map(str -> str.split(","))
                     .map(a -> new ElfPair(new Elf(a[0]), new Elf(a[1])))
                     .map(ElfPair::hasFullyOverlappingSections)
                     .filter(b -> b)
                     .count();
             System.out.println(duplicateSectionsCount);

         } catch (IOException e) {
             e.printStackTrace();
         }

         try (Stream<String> stream = Files.lines(path)) {
             long duplicateSectionsCount = stream.map(str -> str.split(","))
                     .map(a -> new ElfPair(new Elf(a[0]), new Elf(a[1])))
                     .map(ElfPair::hasOverlappingSections)
                     .filter(b -> b)
                     .count();
             System.out.println(duplicateSectionsCount);

         } catch (IOException e) {
             e.printStackTrace();
         }

     }
}
