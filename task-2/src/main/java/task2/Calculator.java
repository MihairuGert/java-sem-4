package task2;

import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
    public void runCalculator(Scanner scanner) {
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
