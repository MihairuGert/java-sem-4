package org.task1;

import java.util.*;

public class Number {
    private String number;
    private String generateNumber(String symbolsPool, int lettersNum) {
        List<Character> symbolsPoolList = new ArrayList<>();
        for (char c : symbolsPool.toCharArray()) {
            symbolsPoolList.add(c);
        }
        Collections.shuffle(symbolsPoolList);
        StringBuilder shuffledString = new StringBuilder();
        int count = 0;
        for (char c : symbolsPoolList) {
            shuffledString.append(c);
            count++;
            if (count == lettersNum) {
                break;
            }
        }
        String shuffledNumber = shuffledString.toString();
        return shuffledNumber;
    }
    Number(String symbolsPool, int lettersNum) {
        number = generateNumber(symbolsPool, lettersNum);
    }
    void printNumber() {
        System.out.println(number);
    }
    String getNumberString() {
        return number;
    }
}
