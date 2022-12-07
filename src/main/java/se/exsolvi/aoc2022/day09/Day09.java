package se.exsolvi.aoc2022.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day09 {
    private final static String input = "input/day09/input.txt";
    private final static String test = "input/day09/test.txt";

    public static void main(String[] args) {
        try {
            new Day09().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run(String... args) {
        Rope rope = new Rope(0,0);

        long count = dataStream(input)
                .flatMap(s -> IntStream.range(0, Integer.parseInt(s.split(" ")[1]))
                        .mapToObj(i -> s.split(" ")[0]))
                .map(str -> {
                    switch (str) {
                        case "R":
                            return rope.moveRight();
                        case "L":
                            return rope.moveLeft();
                        case "U":
                            return rope.moveUp();
                        case "D":
                            return rope.moveDown();
                    }
                    return null;
                })
                .peek(p -> System.out.println(p))
                .map(Rope::getTail)
                .distinct().count();
        System.out.println("Distinct positions: " + count);
    }

    public static List<String> expand(List<String> input) {
        return input.stream()
                .flatMap(s -> IntStream.range(0, Integer.parseInt(s.split(" ")[1]))
                        .mapToObj(i -> s.split(" ")[0]))
                .collect(Collectors.toList());
    }

    private Stream<String> dataStream(String input) {
        Path path = Paths.get(input);
        Supplier<Stream<String>> ss = () -> {
            try {
                return Files.lines(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        return ss.get();
    }
}

