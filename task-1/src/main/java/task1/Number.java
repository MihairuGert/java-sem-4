package task1;

import java.util.*;

public class Number {
    private final String number;
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
        return shuffledString.toString();
    }
    public Number(String symbolsPool, int lettersNum) {
        number = generateNumber(symbolsPool, lettersNum);
    }
    public String getNumberString() {
        return number;
    }
}
