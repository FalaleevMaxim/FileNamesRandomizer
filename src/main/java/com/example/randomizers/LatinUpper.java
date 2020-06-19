package com.example.randomizers;

public class LatinUpper extends AbstractRandomizer {

    protected int alphabetSize() {
        return 26;
    }

    protected char fromNumber(int number) {
        return (char) (number + 'A');
    }
}
