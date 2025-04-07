package task4.utilities;

import java.util.HashMap;
import java.util.Objects;

public class Config {
    private final HashMap<String, String> configTable;

    public Config(String path) {
        configTable = new HashMap<>();
        ConfigParser configParser;
        try {
            configParser = new ConfigParser(path);
        } catch (Exception e1) {
            System.err.println(e1.getMessage());
            try {
                configParser = new ConfigParser(Objects.requireNonNull(Config.class.getResource("/default.txt")).getPath());
            } catch (Exception e2) {
                System.err.println("Fatal: " + e2.getMessage());
                return;
            }
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
        String value = configTable.get(key);
        if (value == null) {
            return "-1";
        }
        return value;
    }
}
