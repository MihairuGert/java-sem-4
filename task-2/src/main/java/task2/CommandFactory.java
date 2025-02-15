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
    public Command newCommand(String string) {
        String className = commandMap.get(string);
        if (className == null) {
            System.out.println("Command with such name does not exist.");
            throw new RuntimeException();
        }
        try {
            return (Command) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    CommandFactory() {
        createCommandMap();
    }
}
