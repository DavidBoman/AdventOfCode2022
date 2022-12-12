package se.exsolvi.aoc2022.day12;

import java.util.Optional;

public class Node {
    private final Node[][] map;
    private final int x;
    private final int y;

    private int value;

    public Node(int x, int y, int value, Node[][] map) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.map = map;
    }
    public Optional<Node> getUp() {
        if (y > 1 && map[x][y-1].value <= value+1) {
            return Optional.of(map[x][y-1]);
        } else {
            return Optional.empty();
        }
    }
    public Optional<Node> getDown() {
        if (y < map.length && map[x][y+1].value <= value+1) {
            return Optional.of(map[x][y+1]);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Node> getLeft() {
        if (x > 0 && map[x-1][y].value <= value+1) {
            return Optional.of(map[x-1][y]);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Node> getRight() {
        if (y < map[x].length && map[x+1][y].value <= value+1) {
            return Optional.of(map[x+1][y]);
        } else {
            return Optional.empty();
        }
    }

    public int getValue() {
        return value;
    }
}
