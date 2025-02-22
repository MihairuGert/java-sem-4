package task3.client;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainWindow extends JFrame {
    private int width;
    private int height;

    Scene scene;

    public MainWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        this.frameInit();
        this.setSize(width, height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setTitle("Shooter the game");

        URL iconURL = getClass().getResource("/icon.jpg");
        if (iconURL == null) {
            throw new RuntimeException("Cannot find icon.jpg");
        }

        ImageIcon icon = new ImageIcon(iconURL);

        this.setIconImage(icon.getImage());

        scene = new Scene();

        this.setVisible(true);
    }

    public void paint(Graphics graphics) {
        scene.paint(graphics);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
