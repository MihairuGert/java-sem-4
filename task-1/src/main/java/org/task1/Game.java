package org.task1;

public class Game {
    private Number number;
    private int lettersNum = 4;
    private String symbolsPool = "0123456789";
    void startGame() {
        number = new Number(symbolsPool, lettersNum);
        //number.printNumber();
        processGame();
    }
    private void processGame() {
        GameInterface gameInterface = new GameInterface();
        gameInterface.printHelp(lettersNum);
        while (true) {
            gameInterface.printGuess();
            Reader reader = new Reader();
            String inputString = reader.getInputString();
            if (inputString.length() != 4) {
                gameInterface.printWrongLength();
                continue;
            }
            if (inputString.equals(number.getNumberString())) {
                gameInterface.printSuccess();
                break;
            }
            gameInterface.printCowsAndBulls(countCows(inputString), countBulls(inputString));
        }
    }
    private int countCows(String string) {
        int count = 0;
        for (char i : number.getNumberString().toCharArray()) {
            for (char j : string.toCharArray()) {
                if (i == j) {
                    count++;
                }
            }
        }
        return count;
    }
    private int countBulls(String string) {
        int count = 0;
        for (int i = 0; i < lettersNum; i++) {
            if (string.charAt(i) == number.getNumberString().charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
