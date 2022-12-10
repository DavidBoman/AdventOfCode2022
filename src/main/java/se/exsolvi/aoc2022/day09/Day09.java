package se.exsolvi.aoc2022.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Day09 {
    private final static String input = "input/day09/input.txt";
    private final static String test = "input/day09/test.txt";
    private final static String test1 = "input/day09/test1.txt";

    private final Rope[] rope = new Rope[10];


    public static void main(String[] args) {
        try {
            new Day09().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void run(String... args) {

        for (int i = 0; i < rope.length; i++) {
            rope[i] = new Rope(0,0);
        }


        long count = dataStreamSupplier(input)
                .map(s -> s.split(" "))
                .flatMap(ss -> IntStream.range(0, Integer.parseInt(ss[1])).mapToObj(i -> ss[0]))
                .peek(dir -> System.out.print(dir + " - "))
                .map(dir -> {
                    Rope ropeTail = rope[0].move(dir);
                    for (int i = 1; i < rope.length; i++) {
                        ropeTail = rope[i].move(ropeTail.getTail());
                    }
                    return rope[8];
                })
                .peek(System.out::println)
                .map(Rope::getTail)
                .distinct()
                .count();

        System.out.println(count);
        for (int i = 0; i < rope.length; i++) {
            System.out.println(rope[i]);
        }
    }

    private Stream<String> dataStreamSupplier(String input) {
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

