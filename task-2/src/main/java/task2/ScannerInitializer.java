package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerInitializer {
    private ReadingMode checkInput(String[] strings) {
        if (strings.length == 0) {
            return ReadingMode.CONSOLE_MODE;
        }
        return strings[0].matches("[A-Za-z0-9А-Яа-я]*.txt") ? ReadingMode.FILE_MODE : ReadingMode.CONSOLE_MODE;
    }
    public Scanner chooseScanner(String[] strings) throws Exception {
        try {
            Scanner scanner = checkInput(strings) == ReadingMode.FILE_MODE ? new Scanner(new File(strings[0])) : new Scanner(System.in);
            scanner.useDelimiter("\n");
            return scanner;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File with this name does not exist.");
        }
    }
}
