package task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        ScannerInitializer scannerInitializer = new ScannerInitializer();
        Scanner scanner;
        try {
            scanner = scannerInitializer.chooseScanner(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        calculator.runCalculator(scanner);
    }
}
