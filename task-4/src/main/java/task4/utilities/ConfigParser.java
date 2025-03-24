package task4.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConfigParser {
    private final Scanner scanner;

    ConfigParser (String path) throws FileNotFoundException {
        scanner = new Scanner(new File(path));
    }

    Pair parseLine() throws Exception{
        String line = scanner.nextLine();
        String[] splitLine = line.split("=");
        if (splitLine.length != 2) {
            throw new Exception("Illegal format: line should look like <key>=<value>");
        }
        Pair pair = new Pair(splitLine[0].trim(), splitLine[1].trim());
        if (!pair.value().matches("[A-Za-z0-9_]+") || !pair.key().matches("[A-Za-z0-9_]+")) {
            throw new Exception("Illegal format: it is possible to use Latin alphabet and digits only");
        }
        return pair;
    }

    boolean hasNext() {
        return scanner.hasNext();
    }
}
