package se.exsolvi.aoc2022.day11;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@RequiredArgsConstructor
public class Muliplication extends Operation {

    private final BigInteger multiplier;
    public Muliplication(int multipler) {
        this.multiplier = BigInteger.valueOf(multipler);
    }
    @Override
    public BigInteger execute(BigInteger value) {
        //System.out.println("    Worry level is multiplied by " + multiplier +  " to " + value.multiply(multiplier) + ".");
        return value.multiply(multiplier);
    }
}
