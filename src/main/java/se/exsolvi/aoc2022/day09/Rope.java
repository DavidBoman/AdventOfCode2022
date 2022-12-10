package se.exsolvi.aoc2022.day09;

public class Rope {
    private Coordinate head;
    private Coordinate tail;

    public Rope(int x, int y) {
        head = new Coordinate(x,y);
        tail = new Coordinate(x,y);
    }

    public Rope move(String d) {
        switch (d) {
            case "R": return move(new Coordinate(head.x+1, head.y));
            case "L": return move(new Coordinate(head.x-1, head.y));
            case "U": return move(new Coordinate(head.x, head.y+1));
            case "D": return move(new Coordinate(head.x, head.y-1));
        }
        throw new RuntimeException("Undefined move");
    }
    public Rope move(Coordinate c) {
        Coordinate oldHead = new Coordinate(head.x, head.y);
        head = new Coordinate(c.x, c.y);
        tail = move(head.x - oldHead.x, head.y - oldHead.y);
        return validate(this);
    }

    private Coordinate move(int directionX, int directionY) {
        Coordinate newTail = new Coordinate(tail.x, tail.y);
        if (head.x == tail.x+2*directionX) {
            newTail.x = newTail.x + directionX;
        }
        if (head.x == tail.x+2*directionX && (head.y == tail.y+directionX || head.y == tail.y-directionX)) {
            newTail.y = head.y;
        }
        if (head.y == tail.y+2*directionY) {
            newTail.y = newTail.y + directionY;
        }
        if (head.y == tail.y+2*directionY && (head.x == tail.x+directionY || head.x == tail.x-directionY)) {
            newTail.x = head.x;
        }
        return newTail;
    }

    private Rope validate(Rope r) {
        if (Math.abs(r.head.x-r.tail.x) < 2 && Math.abs(r.head.y-r.tail.y) < 2) {
            return r;
        } else {
            throw new RuntimeException("Lost my tail: " + r);
        }
    }

    public Coordinate getTail() {
        return tail;
    }
    @Override
    public String toString() {
        return "R[H:" + head + ", T:" + tail + "]";
    }

}
