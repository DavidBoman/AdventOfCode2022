package se.exsolvi.aoc2022;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Day01 implements CommandLineRunner {
    private final String input = "input/day01/input.txt";
    private final Map<Integer, Integer> elfs = new HashMap<>();

    public static void main(String[] args) {
        try {
            new Day01().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(String... args) throws Exception {

        AtomicInteger elf = new AtomicInteger();
        AtomicInteger calorieSum = new AtomicInteger();

        try (Stream<String> stream = Files.lines(Paths.get(input))) {
            stream.forEach(str -> {
                if (!str.equals("")) {
                    calorieSum.addAndGet(Integer.parseInt(str));
                } else {
                    elfs.put(elf.get(), calorieSum.get());
                    calorieSum.set(0);
                    elf.getAndIncrement();
                }
            });

            System.out.println("Max: " + Collections.max(elfs.values()));
            List<Integer> calories = new LinkedList<>();
            calories.addAll(elfs.values());
            Collections.sort(calories);
            Collections.reverse(calories);
            System.out.println("Top three: " + calories.stream().limit(3).reduce(0, Integer::sum));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
