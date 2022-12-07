package se.exsolvi.aoc2022.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Day08 {
    private final static String input = "input/day08/input.txt";
    private final static String test = "input/day08/test.txt";

    private int[][] forest;

    public static void main(String[] args) {
        try {
            new Day08().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run(String... args) throws Exception {

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
        row.set((int) ss.get().map(s-> {
            col.set((int) s.chars().count());
            return s;
        }).count());

        Tree[][] tree = new Tree[row.get()][col.get()];
        row.set(0);
        col.set(0);
        ss.get().forEach(s ->{
            s.chars().map(c -> c - 48).forEach(c -> {
                tree[row.get()][col.get()] = new Tree(c);
                col.incrementAndGet();
            });
            col.set(0);
            row.incrementAndGet();
        });

        // From Left
        int maxHeight = -1;
        int localMaxHeight = Integer.MAX_VALUE;
        for (int y = 0; y < tree.length; y++) {
            for (int x = 0; x < tree[y].length; x++) {
                maxHeight = calculateVisibility(tree[y][x], maxHeight);
                if (tree[x][y].getHeight() > localMaxHeight) {
                    tree[x][y].incVisLeft();
                } else {
                    localMaxHeight = tree[x][y].getHeight();
                }
            }
            maxHeight = -1;
        }
        // From Right
        maxHeight = -1;
        for (int y = 0; y < tree.length; y++) {
            for (int x = tree[0].length-1; x >= 0; x--) {
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
        for (int x = tree[0].length-1; x >= 0; x--) {
            for (int y = tree.length-1; y >= 0; y--) {
                maxHeight = calculateVisibility(tree[y][x], maxHeight);
            }
            maxHeight = -1;
        }

        for (int x = 0; x < tree.length; x++) {
            for (int y = 0; y < tree[x].length; y++) {
                System.out.print(tree[x][y].getHeight());
            }
            System.out.println();
        }
        System.out.println("-----------------");
        int visibleTrees = 0;
        for (int i = 0; i < tree.length; i++) {
            for (int j = 0; j < tree[i].length; j++) {
                if (tree[i][j].isVisible()) {
                    visibleTrees++;
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }
        System.out.println("Number of visible trees: " + visibleTrees);

    }



    private int calculateVisibility(Tree tree, int maxHeight) {
        if (tree.getHeight() > maxHeight) {
            tree.setVisible();
            return tree.getHeight();
        }
        return maxHeight;

    }
}

