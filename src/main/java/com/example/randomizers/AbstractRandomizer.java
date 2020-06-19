package com.example.randomizers;

import java.util.Random;

public abstract class AbstractRandomizer {
    protected final Random random = new Random();

    protected abstract int alphabetSize();

    protected abstract char fromNumber(int number);

    public char random() {
        return fromNumber(random.nextInt(alphabetSize()));
    }
}
