package task4.utilities;

import java.util.HashMap;

public class Config {
    private final HashMap<String, String> configTable;
    private ConfigParser configParser;

    public Config(String path) {
        configTable = new HashMap<>();
        try {
            configParser = new ConfigParser(path);
        } catch (Exception e1) {
            System.err.println(e1.getMessage());
            try {
                configParser = new ConfigParser(Config.class.getResource("/default.txt").getPath());
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
        return configTable.get(key);
    }

    public void DBGMSG() {
        configTable.forEach((String s1, String s2) -> System.out.println(s1 + " " + s2));
    }
}
