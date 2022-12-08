package se.exsolvi.aoc2022.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class Day12 {
    private final static String input = "input/day12/input.txt";
    private final static String test = "input/day12/test.txt";

    public static void main(String[] args) {
        try {
            new Day12().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int[][] map;

    public void run(String... args) {

        AtomicInteger cols = new AtomicInteger();
        AtomicInteger rows = new AtomicInteger();
        rows.set((int)dataStreamSupplier(test).peek(str -> {
            cols.set((int)str.chars().count());
        }).count());
        map = new int[cols.get()][rows.get()];

        Coordinate start;
        Coordinate end;
        int y = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(test))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                int x = 0;
                while (x < line.length()) {
                    char c = line.charAt(x);
                    if (c == 'S') {
                       start = new Coordinate(x,y);
                       c = 'a';
                    } else if ( c == 'E') {
                        end = new Coordinate(x,y);
                        c = 'z';
                    }
                    map[x][y] = ((int)c)-96;
                    x++;
                }
                y++;
            }
        } catch (IOException e) {
            // Handle the IOException
        }

        Arrays.stream(map).peek(System.out::println).forEach(str -> Arrays.stream(str).mapToObj(i -> String.valueOf(i)).forEach(System.out::print));

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

