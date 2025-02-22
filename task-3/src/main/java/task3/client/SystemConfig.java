package task3.client;

import java.awt.*;

public class SystemConfig {
    private final Dimension screenSize;

    public Dimension getScreenSize() {
        return screenSize;
    }

    public SystemConfig() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }
}
