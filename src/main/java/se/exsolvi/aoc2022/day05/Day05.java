package se.exsolvi.aoc2022.day05;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day05 {
    private final String input = "input/day05/input.txt";
    private final String test = "input/day05/test.txt";
    private final Map<Integer, LinkedList<Crate>> crane = new HashMap<>();
    private final Map<Integer, Stack<Crate>> ship = new HashMap<>();

    public static void main(String[] args) {
        try {
            new Day05().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run(String... args) throws Exception {

        Path path = Paths.get(input);
        try (Stream<String> stream = Files.lines(path)) {
            stream.filter(row -> row.contains("["))
                    //.peek(this::l)
                    .forEach(row -> {
                        for (int i = 0; i < row.length(); i++) {
                            //l(i + ": " + String.valueOf(row.charAt(i)));
                            if (row.charAt(i) != ' ' && row.charAt(i) != '[' && row.charAt(i) != ']') {
                                if (crane.get(i) == null) {
                                    l("Creating stack at " + i);
                                    crane.put(i, new LinkedList<Crate>());
                                }
                                l("Pushing crate " + row.charAt(i) + " onto stack " + i);
                                crane.get(i).addFirst(new Crate(row.charAt(i)));
                            }
                        }
                    });
            l("Ship contains");
            int j = 1;
            for (int i = 0; i < 9000; i++) {
                if (crane.get(i) != null) {
                    ship.put(j, new Stack<Crate>());
                    int finalJ = j;
                    crane.get(i).stream().forEach(c -> ship.get(finalJ).push(c));
                    j++;
                }
            }

            for (int i = 1; i < 15; i++) {
                if (ship.get(i) != null) {
                    l("Stack " + i);
                    ship.get(i).stream().forEach(c -> l(c.toString()));
                }
            }
        }

        l("Starting to move crates");
        try (Stream<String> stream = Files.lines(path)) {
            stream.filter(row -> row.contains("move"))
                    .forEach(row -> {
                        Pattern r = Pattern.compile("move (\\d*) from (\\d*) to (\\d*)");

                        // Now create matcher object.
                        Matcher m = r.matcher(row);

                        if (m.find()) {
                            LinkedList<Crate> pile = new LinkedList<>();
                            for (int move = 0; move < Integer.parseInt(m.group(1)); move++) {
                                l("Moving crate " + ship.get(Integer.parseInt(m.group(2))).peek() + " from " + m.group(2) + " to " + m.group(3));
                                pile.addFirst(ship.get(Integer.parseInt(m.group(2))).pop());
                            }
                            ship.get(Integer.parseInt(m.group(3))).addAll(pile);
                            // Original order: [H][N][S][N][M][T][L][H][Q]
                        }

                    });
        }

        l("Let's look at the top: ");
        for (int i = 0; i < 100; i++ ) {
            if (ship.get(i) != null) {
              System.out.print(ship.get(i).peek());
            }
        }
    }

    private void l(String str) {
        System.out.println(str);
    }


}
