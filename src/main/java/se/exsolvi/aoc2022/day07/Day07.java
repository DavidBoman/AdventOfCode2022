package se.exsolvi.aoc2022.day07;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day07 {
    private final static String input = "input/day07/input.txt";
    private final static String test = "input/day07/test.txt";

    public static void main(String[] args) {
        try {
            new Day07().run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run(String... args) throws Exception {

        Folder root = new Folder( "/", null);

        Path path = Paths.get(input);
        try (Stream<String> stream = Files.lines(path)) {
            var fs = new Object() {
                Folder cwd = root;
            };
            stream
                    .filter(s -> !s.contains("$ ls"))
                    .forEach(s -> {
                if (s.startsWith("$ ls")) {
                    // Discard
                } else if (s.startsWith("$ cd")) {
                        fs.cwd = fs.cwd.chdir(getPart(s, 3));
                } else if (s.startsWith(("dir "))) {
                    fs.cwd.createFolder(getPart(s, 2));
                } else {
                    fs.cwd.createFile(getPart(s, 2), Integer.parseInt(getPart(s,1)));
                }
            });
            System.out.println(root.getSize());
            System.out.println("Sum of folders > 100000: " +
                    root.getFolders(true).stream()
                    .map(Folder::getSize)
                    .filter(size -> size < 100000)
                    .reduce(0, Integer::sum));
            int freeSpace = 70000000 - root.getSize();
            int needToReclaim = 30000000 - freeSpace;
            System.out.println("Free space: " + freeSpace);
            System.out.println("Needs to reclaim: " + needToReclaim);
            System.out.println("Size of selected directory: " + root.getFolders(true).stream().map(Folder::getSize).filter(size -> size >= needToReclaim).reduce(Integer.MAX_VALUE, Integer::min));
        }
    }

    private static String getPart(String s, int part) {
        if (part == 1) {
            return s.substring(0, s.indexOf(" ")).trim();
        } else {
            return s.substring(s.indexOf(" ", part-1)).trim();
        }
    }


}
