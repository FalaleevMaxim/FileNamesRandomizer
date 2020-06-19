package com.example.randomizers;

import java.util.ArrayList;
import java.util.List;

public class RandomizerCombo extends AbstractRandomizer {
    private List<AbstractRandomizer> randomizers;

    public RandomizerCombo(List<AbstractRandomizer> randomizers) {
        this.randomizers = randomizers;
    }

    public RandomizerCombo() {
        this.randomizers = new ArrayList<>();
    }

    protected int alphabetSize() {
        return randomizers.stream().mapToInt(AbstractRandomizer::alphabetSize).sum();
    }

    protected char fromNumber(int number) {
        for (AbstractRandomizer randomizer : randomizers) {
            if (number < randomizer.alphabetSize()) {
                return randomizer.random();
            }
            number -= randomizer.alphabetSize();
        }
        throw new IllegalStateException("Random number is too big");
    }

    public RandomizerCombo addRandomizer(AbstractRandomizer randomizer) {
        randomizers.add(randomizer);
        return this;
    }
}