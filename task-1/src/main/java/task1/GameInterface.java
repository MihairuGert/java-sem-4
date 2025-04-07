package task1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameInterface {

    private static final Logger logger = LoggerFactory.getLogger(GameInterface.class);

    void printGuess() {
        logger.info("Отгадывайте: ");
    }
    void printWrongLength() {
        logger.warn("Не правильная длина!");
    }
    void printSuccess() {
        logger.info("Успех!");
    }
    void printCowsAndBulls(int cowsCount, int bullsCount) {
        logger.info(cowsCount + " Коров " + bullsCount + " Быков");
    }
    void printHelp(int lettersCount) {
        logger.info("Эта игра Быки и Коровы. Компьютер загадал число из " +  " lettersCount неповторяющихся цифер. Успехов!");
    }
}
