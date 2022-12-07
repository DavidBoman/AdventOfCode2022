package se.exsolvi.aoc2022.day09;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Rope {

    @Getter
    @NonNull
    private Coordinate head;
    @Getter
    @NonNull
    private Coordinate tail;

    public Rope(int x, int y) {
        this.head = new Coordinate(x,y);
        this.tail = new Coordinate(x,y);
    }

    public Rope moveUp() {
        head.y++;
        if (head.y == tail.y+2) {
            tail.y++;
            if (head.x == tail.x-1 || head.x == tail.x+1) {
                tail.x = head.x;
            }
        }
        return validate(this);
    }

    private Rope validate(Rope rope) {
        if (Math.abs(rope.tail.x-rope.head.x) > 2 || Math.abs(rope.tail.y-rope.head.y) > 2) {
            throw new RuntimeException("Lost the tail: " + rope);
        }
        return rope;
    }

    public Rope moveDown() {
        head.y--;
        if (head.y == tail.y-2) {
            tail.y--;
            if (head.x == tail.x-1 || head.x == tail.x+1) {
                tail.x = head.x;
            }
        }
        return validate(this);
    }

    public Rope moveRight() {
        head.x++;
        if (head.x == tail.x+2) {
            tail.x++;
            if (head.y == tail.y-1 || head.y == tail.y+1) {
                tail.y = head.y;
            }
        }
        return validate(this);
    }

    public Rope moveLeft() {
        head.x--;
        if (head.x == tail.x-2) {
            tail.x--;
            if (head.y == tail.y-1 || head.y == tail.y+1) {
                tail.y = head.y;
            }
        }
        return validate(this);
    }

    public String toString() {
        return "R(H: " + head.x + "," + head.y + " T: " + tail.x + "," + tail.y + ")";
    }

}
