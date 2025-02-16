package task1;

public class GameInterface {
    void printGuess() {
        System.out.println("Отгадывайте: ");
    }
    void printWrongLength() {
        System.out.println("Не правильная длина!");
    }
    void printSuccess() {
        System.out.println("Успех!");
    }
    void printCowsAndBulls(int cowsCount, int bullsCount) {
        System.out.println(cowsCount + " Коров " + bullsCount + " Быков");
    }
    void printHelp(int lettersCount) {
        System.out.printf("Эта игра Быки и Коровы. Компьютер загадал число из %d неповторяющихся цифер. Успехов! ", lettersCount);
    }
}
