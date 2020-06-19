package com.example.randomizers;

public class CyrillicLower extends AbstractRandomizer {
    protected int alphabetSize() {
        return 32;
    }

    protected char fromNumber(int number) {
        return (char) (number + 'а');
    }
}