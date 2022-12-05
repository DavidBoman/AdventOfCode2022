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

@SpringBootApplication
public class Day00 implements CommandLineRunner {
    private final String input = "input/day01/input.txt";
    private final Map<Integer, Integer> elfs = new HashMap<>();

    public static void main(String[] args) {
        try {
            System.setProperty("spring.devtools.restart.enabled", "false");
            SpringApplication.run(Day00.class, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("************************************'");
        System.out.println("** Welcome to Advent of Code 2022 **'");
        System.out.println("************************************'");
    }
}

