package task4.utilities;

import java.util.HashMap;

public class Config {
    private final HashMap<String, String> configTable;
    private ConfigParser configParser;

    public Config(String path) {
        configTable = new HashMap<>();
        try {
            configParser = new ConfigParser(path);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            // todo run with default config
        }
        while (configParser.hasNext()) {
            try {
                Pair pair = configParser.parseLine();
                configTable.put(pair.key(), pair.value());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public String getFieldValue(String key) {
        return configTable.get(key);
    }
}
