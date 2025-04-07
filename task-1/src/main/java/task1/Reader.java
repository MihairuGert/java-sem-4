package task1;

import java.util.Scanner;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);
    public String getInputString() {
        return scanner.nextLine();
    }
}
