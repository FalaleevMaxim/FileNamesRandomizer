package com.example.randomizers;

public class Numbers extends AbstractRandomizer {
    protected int alphabetSize() {
        return 10;
    }

    protected char fromNumber(int number) {
        return (char) (number + '0');
    }
}
