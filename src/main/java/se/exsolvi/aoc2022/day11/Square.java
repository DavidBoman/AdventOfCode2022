package se.exsolvi.aoc2022.day11;

import java.math.BigInteger;

public class Square extends Operation {

    @Override
    public BigInteger execute(BigInteger value) {
        //System.out.println("    Worry level is multiplied by itself to " + value.pow(2) + ".");
        return value.pow(2);
    }
}
