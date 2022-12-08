package se.exsolvi.aoc2022.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Day08 {
    private final static String input = "input/day08/input.txt";
    private final static String test = "input/day08/test.txt";

    public static void main(String[] args) {
        try {
            new Day08().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run(String... args) {

        Path path = Paths.get(input);
        Supplier<Stream<String>> ss = () -> {
            try {
                return Files.lines(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        final AtomicInteger row = new AtomicInteger();
        final AtomicInteger col = new AtomicInteger();
        row.set((int) ss.get().peek(s -> col.set((int) s.chars().count())).count());

        Tree[][] tree = new Tree[row.get()][col.get()];
        row.set(0);
        col.set(0);
        ss.get().forEach(s -> {
            s.chars().map(c -> c - 48).forEach(c -> {
                tree[col.get()][row.get()] = new Tree(tree, col.get(), row.get(), c);
                col.incrementAndGet();
            });
            col.set(0);
            row.incrementAndGet();
        });

        // From Left
        int maxHeight = -1;
        for (int y = 0; y < tree.length; y++) {
            for (int x = 0; x < tree[y].length; x++) {
                maxHeight = calculateVisibility(tree[y][x], maxHeight);
            }
            maxHeight = -1;
        }
        // From Right
        maxHeight = -1;
        for (int y = 0; y < tree.length; y++) {
            for (int x = tree[0].length - 1; x >= 0; x--) {
                maxHeight = calculateVisibility(tree[y][x], maxHeight);
            }

            maxHeight = -1;
        }

        // From Top
        maxHeight = -1;
        for (int x = 0; x < tree[0].length; x++) {
            for (int y = 0; y < tree.length; y++) {
                maxHeight = calculateVisibility(tree[y][x], maxHeight);
            }
            maxHeight = -1;
        }
        // From Bottom
        maxHeight = -1;
        for (int x = tree[0].length - 1; x >= 0; x--) {
            for (int y = tree.length - 1; y >= 0; y--) {
                maxHeight = calculateVisibility(tree[y][x], maxHeight);
            }
            maxHeight = -1;
        }

        for (int y = 0; y < tree.length; y++) {
            for (int x = 0; x < tree[y].length; x++) {
                System.out.print(tree[x][y].getHeight());
            }
            System.out.println();
        }
        System.out.println("-----------------");
        int visibleTrees = 0;
        for (int y = 0; y < tree.length; y++) {
            for (int x = 0; x < tree[y].length; x++) {
                if (tree[x][y].isVisible()) {
                    visibleTrees++;
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }
        System.out.println("Number of visible trees: " + visibleTrees);

        System.out.println("-----------------");
        for (int y = 0; y < tree.length; y++) {
            for (int x = 0; x < tree[y].length; x++) {
                System.out.print("|" + tree[x][y].getScenicScore() + "|");
            }
            System.out.println();
        }
        System.out.println("Max LoS: " + Arrays.stream(tree).flatMap(r -> Arrays.stream(r)).map(Tree::getScenicScore).reduce(Integer.MIN_VALUE, Integer::max));

    }
    private int calculateVisibility(Tree tree, int maxHeight) {
        if (tree.getHeight() > maxHeight) {
            tree.setVisible();
            return tree.getHeight();
        }
        return maxHeight;

    }
}

