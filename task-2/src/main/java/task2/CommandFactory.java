package task2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;

public class CommandFactory {
    private final HashMap<String, String> commandMap = new HashMap<>();
    private void createCommandMap() {
        InputStream inputStream = CommandFactory.class.getResourceAsStream("/commands.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            for (String commandName : properties.stringPropertyNames()) {
                String className = properties.getProperty(commandName);
                commandMap.put(commandName, className);
            }
        } catch (IOException e) {
            System.out.println("Unable to load commands.properties");
        }
    }
    public Command newCommand(String commandName) throws RuntimeException {
        String className = commandMap.get(commandName);
        if (className == null) {
            throw new RuntimeException("Command with name \"" + commandName + "\" does not exist.");
        }
        try {
            return (Command) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public CommandFactory() {
        createCommandMap();
    }
}
