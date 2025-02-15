package task2;

import task2.commands.PopCommand;
import task2.commands.PrintCommand;
import task2.commands.PushCommand;
import task2.commands.binaryOps.SumCommand;

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
        Scanner scanner = chooseScanner(strings);
        InputParser inputParser = new InputParser();
        Context context = new Context();
        while (scanner.hasNext()) {
            String string = scanner.next();
            Command command = null;
            String commandName = inputParser.getCommandName(string);
            switch (commandName) {
                case "PUSH":
                    command = new PushCommand();
                    break;
                case "POP":
                    command = new PopCommand();
                    break;
                case "PRINT":
                    command = new PrintCommand();
                    break;
                case "SUM":
                    command = new SumCommand();
                    break;
                default:
                    continue;
            }
            command.execute(context, inputParser.getAttributes(string));
        }
    }
}
/*
* if (attributes[0].equals("PUSH")) {
            return new PushCommand();
        }
        else if (attributes[0].equals("POP")) {
            return new PopCommand();
        }*/