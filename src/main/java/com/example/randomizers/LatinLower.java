package com.example.randomizers;

public class LatinLower extends AbstractRandomizer {

    protected int alphabetSize() {
        return 26;
    }

    protected char fromNumber(int number) {
        return (char) (number + 'a');
    }
}
