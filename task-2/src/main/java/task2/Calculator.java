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
        Scanner scanner = chooseScanner(strings);
        InputParser inputParser = new InputParser();
        Context context = new Context();
        while (scanner.hasNext()) {
            String inputString = scanner.next();
            Command command;
            CommandFactory commandFactory = new CommandFactory();
            String commandName = inputParser.getCommandName(inputString);
            if (commandName == null) {
                continue;
            }
            try {
                command = commandFactory.newCommand(commandName);
                command.execute(context, inputParser.getAttributes(inputString));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
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