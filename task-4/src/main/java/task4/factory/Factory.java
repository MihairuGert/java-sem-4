package task4.factory;

import task4.utilities.Config;

public class Factory {
    private Config config;
    //todo Move to constructor
    public void run(String path) {
        config = new Config(path);
        System.out.println(config.getFieldValue("aboba"));
    }
}
