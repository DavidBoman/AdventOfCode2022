package se.exsolvi.aoc2022.day11;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Monkey {

    private static Map<Integer, Monkey> monkeyPack;

    private final AtomicInteger inspections = new AtomicInteger();
    private final int identifier;
    private final LinkedList<BigInteger> items = new LinkedList<>();
    private final Operation op;
    private final Division test;
    private final int targetTrue;
    private final int targetFalse;

    public Monkey(int identifier, List<Integer> items, Operation op, Division test, int targetTrue, int targetFalse) {
        if (monkeyPack == null) {
            monkeyPack = new HashMap<>();
        }
        monkeyPack.put(identifier, this);
        this.identifier = identifier;
        items.stream().map(BigInteger::valueOf).forEach(this.items::addLast);
        this.op = op;
        this.test = test;
        this.targetTrue = targetTrue;
        this.targetFalse = targetFalse;
    }

    public void inspect() {
        int length = items.size();
        for (int i = 0; i < length; i++) {
            BigInteger worry = items.removeFirst();
            //System.out.println("  Monkey inspects an item with a worry level of " + worry + ".");
            worry = op.execute(worry);
            worry = worry.mod(BigInteger.valueOf(9699690));
            //worry = worry.divide(BigInteger.valueOf(1));
            //System.out.println("    Monkey gets bored with item. Worry level is divided by 1 to " + (worry) + ".");
            Monkey target = test.isDivisible(worry) ? monkeyPack.get(targetTrue) : monkeyPack.get(targetFalse);
            if (target == null) {
                throw new RuntimeException("Monkey not found");
            }
            //System.out.println("    Item with worry level " + worry + " is thrown to monkey " + target + ".");
            target.addItem(worry);
            inspections.addAndGet(1);
        }
    }

    public long getNumberOfInspections() {
        return inspections.get();
    }

    public void addItem(BigInteger item) {
        items.addLast(item);
    }

    public String toString() {
        return String.valueOf(identifier);
    }

}
