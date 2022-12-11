package se.exsolvi.aoc2022.day11;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

public class Addition extends Operation {

    private final BigInteger additive;

    public Addition(int additive) {
        this.additive = BigInteger.valueOf(additive);
    }

    @Override
    public BigInteger execute(BigInteger value) {
        //System.out.println("    Worry level increases by " + additive + " to " + value.add(additive) + ".");
        return value.add(additive);
    }
}
