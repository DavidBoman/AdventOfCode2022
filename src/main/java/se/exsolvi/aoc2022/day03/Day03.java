package se.exsolvi.aoc2022.day03;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day03 {
    private final String input = "input/day03/input.txt";
    private final String test = "input/day03/test.txt";
    private List<RuckSack> ruckSackList;

    public Day03() {
    }
    public static void main(String[] args) {
        try {
            new Day03().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isUpper(char c) {
        String s = String.valueOf(c);
        return s.toUpperCase().equals(s);
    }

    private int getPriority(char c) {
        if (isUpper(c)) {
            return c - 38;
        } else {
            return c - 96;
        }
    }
    public void run(String... args) throws Exception {

        Path path = Paths.get(input);
        try (Stream<String> stream = Files.lines(path)) {
            ruckSackList = stream.map(str -> new RuckSack(str)).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Priority sum: " + ruckSackList.stream().map(rs -> rs.getUniqCommonAsString().charAt(0)).map(c-> getPriority(c)).reduce(0, Integer::sum));

        AtomicInteger counter = new AtomicInteger();
        Collection<List<RuckSack>> groups = ruckSackList.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 3)).values();

        Integer badgeSum = groups.stream().map(g -> {
            Set<Character> common = new HashSet<>(g.get(0).asList());
            for (int i = 1; i < g.size(); i++) {
                common.retainAll(g.get(i).asList());
            }
            return common.stream().findFirst().get();
        }).map(c -> getPriority(c)).reduce(0, Integer::sum);
        System.out.println(("Badge sum: " + badgeSum));
    }
}
