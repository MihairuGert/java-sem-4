package task3.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Scene extends JPanel {
    protected final Dimension dimension;

    public Scene(Dimension screenSize) {
        this.setPreferredSize(screenSize);
        dimension = screenSize;
    }
}
