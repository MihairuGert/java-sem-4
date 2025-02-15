package task2;

import task2.commands.PopCommand;
import task2.commands.PushCommand;

import java.util.Arrays;

public class InputParser {
    private final String regex = " ";

    public String getCommandName(String inputString) {
        String[] name = inputString.split(regex);
        return name[0].trim();
    }
    public String[] getAttributes(String inputString) {
        String[] attributes = inputString.split(regex);
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = attributes[i].trim();
        }
        return Arrays.copyOfRange(attributes, 1, attributes.length);
    }
}
