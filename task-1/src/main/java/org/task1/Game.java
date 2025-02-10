package org.task1;

public class Game {
    private Number number;
    private int lettersNum = 4;
    private String symbolsPool = "0123456789";
    void startGame() {
        number = new Number(symbolsPool, lettersNum);
        //number.printNumber();
        while (true) {
            System.out.println("Guess: ");
            Reader reader = new Reader();
            String inputString = reader.getInputString();
            if (inputString.length() != 4) {
                System.out.println("Wrong length!");
                continue;
            }
            if (inputString.equals(number.getNumberString())) {
                System.out.println("Correct!");
                break;
            }
            System.out.println(countCows(inputString) + " Коров " + countBulls(inputString) + " Быков");
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
