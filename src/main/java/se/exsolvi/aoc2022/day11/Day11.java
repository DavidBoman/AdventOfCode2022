package se.exsolvi.aoc2022.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class Day11 {
    private final static String input = "input/day11/input.txt";
    private final static String test = "input/day11/test.txt";
    private final static String shortTest = "input/day11/short.txt";
    private static final int NUMBER_OF_ROUNDS = 10000;

    public static void main(String[] args) {
        try {
            new Day11().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void run(String... args) {
/*
        LinkedList<Monkey> monkeys = new LinkedList<>();
        monkeys.addLast(new Monkey(0, List.of(79, 98), new Muliplication(19), new Division(23), 2, 3));
        monkeys.addLast(new Monkey(1, List.of(54, 65, 75, 74), new Addition(6), new Division(19), 2, 0));
        monkeys.addLast(new Monkey(2, List.of(79, 60, 97), new Square(), new Division(13), 1, 3));
        monkeys.addLast(new Monkey(3, List.of(74), new Addition(3), new Division(17), 0, 1));
*/
        // 250425
        LinkedList<Monkey> monkeys = new LinkedList<>();
        monkeys.addLast(new Monkey(0, List.of(71, 56, 50, 73), new Muliplication(11), new Division(13), 1, 7));
        monkeys.addLast(new Monkey(1, List.of(70, 89, 82), new Addition(1), new Division(7), 3, 6));
        monkeys.addLast(new Monkey(2, List.of(52, 95), new Square(), new Division(3), 5, 4));
        monkeys.addLast(new Monkey(3, List.of(94, 64, 69, 87, 70), new Addition(2), new Division(19), 2, 6));
        monkeys.addLast(new Monkey(4, List.of(98, 72, 98, 53, 97, 51), new Addition(6), new Division(5), 0, 5));
        monkeys.addLast(new Monkey(5, List.of(79), new Addition(7), new Division(2), 7, 0));
        monkeys.addLast(new Monkey(6, List.of(77, 55, 63, 93, 66, 90, 88, 71), new Muliplication(7), new Division(11), 2, 4));
        monkeys.addLast(new Monkey(7, List.of(54, 97, 87, 70, 59, 82, 59), new Addition(8), new Division(17), 1, 3));


        for (int i = 0; i < NUMBER_OF_ROUNDS; i++) {
            System.out.println("Round: " + i);
            monkeys.stream().peek(m -> System.out.println("Monkey " + m + ":")).forEach(Monkey::inspect);
        }
        monkeys.stream().forEach(m -> System.out.println("Monkey " + m + " inspected items " + m.getNumberOfInspections() + " times."));
        Long value = monkeys.stream().map(Monkey::getNumberOfInspections).sorted(Collections.reverseOrder()).limit(2).reduce(1l, (a, b) -> a * b);
        System.out.println(value);
        // Too low: 20734415974
        // Too low: 14400359996
        // Correct: 21800916620

    }

    private Stream<String> dataStreamSupplier(String input) {
        Supplier<Stream<String>> ss = () -> {
            try {
                return Files.lines(Paths.get(input));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        return ss.get();
    }
}

