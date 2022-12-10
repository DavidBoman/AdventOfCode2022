package se.exsolvi.aoc2022.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Day10 {
    private final static String input = "input/day10/input.txt";
    private final static String test = "input/day10/test.txt";
    private final static String shortTest = "input/day10/short.txt";
    private Set<String> sampler = new HashSet<>();

    public static void main(String[] args) {
        try {
            new Day10().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void run(String... args) {

        AtomicInteger clock = new AtomicInteger(1);
        AtomicInteger x = new AtomicInteger(1);

        sampler.add("20");
        sampler.add("60");
        sampler.add("100");
        sampler.add("140");
        sampler.add("180");
        sampler.add("220");

        long sum = dataStreamSupplier(input)
                .map(s -> s.split(" "))
                .flatMap(ss -> {
                    switch (ss[0]) {
                        case "noop": return IntStream.range(0, 1).mapToObj(i -> "noop");
                        case "addx": return IntStream.range(0, 2).mapToObj(i -> i == 0 ? "addx 0" : "addx " + ss[1]);
                    }
                    throw new RuntimeException("Unexpected op-code: " + ss[0]);
                })
                .peek(op -> clock.incrementAndGet())
                .peek(op -> {
                    if (op.startsWith("addx")) {
                        x.addAndGet(Integer.parseInt(op.split(" ")[1]));
                    }
                })
               .map(s -> clock.get() + " " + x.get() + " " + s)
                .filter(string -> sampler.contains(string.split(" ")[0]))
                //.peek(System.out::println)
                .map(s -> s.split(" "))
                .map(s -> Integer.parseInt(s[0])*Integer.parseInt(s[1]))
                //.peek(System.out::println)
                .reduce(0, Integer::sum);
        System.out.println(sum);

        clock.set(1);
        x.set(1);

        long count = dataStreamSupplier(input)
                .map(s -> s.split(" "))
                .flatMap(ss -> {
                    switch (ss[0]) {
                        case "noop": return IntStream.range(0, 1).mapToObj(i -> "noop");
                        case "addx": return IntStream.range(0, 2).mapToObj(i -> i == 0 ? "addx 0" : "addx " + ss[1]);
                    }
                    throw new RuntimeException("Unexpected op-code: " + ss[0]);
                })
                .peek(s -> {
                    int cursor = (clock.get()-1)%40;
                    if (cursor >= x.get()-1 && cursor <= x.get()+1) {
                        System.out.print("#");
                    } else {
                        System.out.print(".");
                    }
                })
                .peek(op -> clock.incrementAndGet())
                .peek(op -> {
                    if (op.startsWith("addx")) {
                        x.addAndGet(Integer.parseInt(op.split(" ")[1]));
                    }
                })
                .map(s -> clock.get())
                .peek(s -> { if ((s-1)%40 == 0) { System.out.println(); }})
                .count();
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

