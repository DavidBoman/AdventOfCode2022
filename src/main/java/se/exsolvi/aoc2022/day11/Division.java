package se.exsolvi.aoc2022.day11;

import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@RequiredArgsConstructor
public class Division extends Operation {
    private final BigInteger divisor;

    public Division(int divisor) {
        this.divisor = BigInteger.valueOf(divisor);
    }
    @Override
    public BigInteger execute(BigInteger value) {
        return value.divide(divisor);
    }

    public BigInteger getReminder(BigInteger value) {
        return value.mod(divisor);
    }

    public boolean isDivisible(BigInteger value) {
        boolean divisible = value.mod(divisor).equals(BigInteger.valueOf(0));
        if (divisible) {
            System.out.println("    Current worry level is divisible by " + divisor + ".");
        } else {
            System.out.println("    Current worry level is not divisible by " + divisor + ".");
        }
        return divisible;
    }
}
