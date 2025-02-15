package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Calculator {
    private ReadingMode checkInput(String[] strings) {
        if (strings.length == 0) {
            return ReadingMode.CONSOLE_MODE;
        }
        return strings[0].matches("[A-Za-z0-9А-Яа-я]*.txt") ? ReadingMode.FILE_MODE : ReadingMode.CONSOLE_MODE;
    }
    private Scanner chooseScanner(String[] strings) {
        try {
            Scanner scanner = checkInput(strings) == ReadingMode.FILE_MODE ? new Scanner(new File(strings[0])) : new Scanner(System.in);
            scanner.useDelimiter("\n");
            return scanner;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void runCalculator(String[] strings) {
        System.out.print("hello!!");
        Scanner scanner = chooseScanner(strings);
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
    Calculator() {
    }
}
