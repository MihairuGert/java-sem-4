package task1;

public class Game {
    private Number number;
    private final int lettersNum = 4;
    private final String symbolsPool = "0123456789";
    public String getRightAnswer() {
        if (number == null)
            number = new Number(symbolsPool, lettersNum);
        return number.getNumberString();
    }
    public void startGame() {
        if (number == null)
            number = new Number(symbolsPool, lettersNum);
        processGame();
    }
    private void processGame() {
        GameInterface gameInterface = new GameInterface();
        gameInterface.printHelp(lettersNum);
        Reader reader = new Reader();
        while (true) {
            gameInterface.printGuess();
            String inputString = reader.getInputString();
            if (inputString.length() != 4) {
                gameInterface.printWrongLength();
                continue;
            }
            if (inputString.equals(number.getNumberString())) {
                gameInterface.printSuccess();
                break;
            }
            gameInterface.printCowsAndBulls(countCows(inputString) - countBulls(inputString), countBulls(inputString));
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
