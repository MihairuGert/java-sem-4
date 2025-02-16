package task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        ScannerInitializer scannerInitializer = new ScannerInitializer();
        Scanner scanner;
        try {
            scanner = scannerInitializer.chooseScanner(args);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return;
        }
        calculator.runCalculator(scanner);
    }
}
