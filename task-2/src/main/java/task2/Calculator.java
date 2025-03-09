package task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

    public void runCalculator(Scanner scanner) {
        logger.info("Calculator has successfully ran.");
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
                logger.warn(e.getMessage());
            }
        }
        logger.info("Calculator has successfully finished its work.");
    }
}
