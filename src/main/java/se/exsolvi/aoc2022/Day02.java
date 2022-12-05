package se.exsolvi.aoc2022;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Day02 {
    private final String input = "input/day02/input.txt";
    private final String test = "input/day02/test.txt";
    private final Map<String, Integer> moveMap = new HashMap<>();
    private final Map<String, Integer> gameMap = new HashMap<>();

    public Day02() {
        // A Rock, B Paper, C Scissor
        // X Rock, Y Paper, Z Scissor
        // 1 Rock, 2 Paper, 3 Scissor
        moveMap.put("AX", 4); // Rock     Rock     Draw  1 3 4
        moveMap.put("AY", 8); // Rock     Paper    Win   2 6 8
        moveMap.put("AZ", 3); // Rock     Scissors Loose 3 0 3
        moveMap.put("BX", 1); // Paper    Rock     Loose 1 0 1
        moveMap.put("BY", 5); // Paper    Paper    Draw  2 3 5
        moveMap.put("BZ", 9); // Paper    Scissors Win   3 6 9
        moveMap.put("CX", 7); // Scissors Rock     Win   1 6 7
        moveMap.put("CY", 2); // Scissors Paper    Loose 2 0 2
        moveMap.put("CZ", 6); // Scissors Scissors Draw  3 3 6

        // A Rock, B Paper, C Scissor
        // X Loose, Y Draw, Z Win
        // 1 Rock, 2 Paper, 3 Scissor
        gameMap.put("AX", 3); // Rock     Loose Scissors 0 3 3
        gameMap.put("AY", 4); // Rock     Draw  Rock     3 1 4
        gameMap.put("AZ", 8); // Rock     Win   Paper    6 2 8
        gameMap.put("BX", 1); // Paper    Loose Rock     0 1 1
        gameMap.put("BY", 5); // Paper    Draw  Paper    3 2 5
        gameMap.put("BZ", 9); // Paper    Win   Scissors 6 3 9
        gameMap.put("CX", 2); // Scissors Loose Paper    0 2 2
        gameMap.put("CY", 6); // Scissors Draw  Scissors 3 3 6
        gameMap.put("CZ", 7); // Scissors Win   Rock     6 1 7
    }

    public static void main(String[] args) {
        try {
            new Day02().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run(String... args) throws Exception {

        Path path = Paths.get(input);

        try (Stream<String> stream = Files.lines(path)) {
            System.out.println("Score 1: " + stream.map(str -> str.replace(" ", "")).map(str -> moveMap.get(str)).reduce(0, Integer::sum));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<String> stream = Files.lines(path)) {
            System.out.println("Score 2: " + stream.map(str -> str.replace(" ", "")).map(str -> gameMap.get(str)).reduce(0, Integer::sum));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
