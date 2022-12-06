package se.exsolvi.aoc2022.day06;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day06 {
    public static final int MARKER_LENGTH = 14;
    private final String input = "input/day06/input.txt";
    private final String test = "input/day06/test.txt";

    public static void main(String[] args) {
        try {
            new Day06().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run(String... args) throws Exception {

        Path path = Paths.get(input);
        try (Stream<String> stream = Files.lines(path)) {
            stream.map(s -> this.findMarker(s)).forEach(System.out::println);
        }
    }

    private int findMarker(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (i < MARKER_LENGTH) {
                continue;
            }
            String sample = s.substring(i-MARKER_LENGTH, i);
            if (sample.chars().distinct().count() == MARKER_LENGTH) {
                return i;
            }
        }
        return -1;
    }


    private void l(String str) {
        System.out.println(str);
    }


}
