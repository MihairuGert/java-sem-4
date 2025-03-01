package task3.view;

import task3.controller.PlayerController;

import javax.swing.*;
import java.net.URL;

public class MainWindow extends JFrame {
    private boolean isVisible = false;

    public MainWindow() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setTitle("Shooter the game");

        URL iconURL = getClass().getResource("/icon.jpg");
        if (iconURL == null) {
            throw new RuntimeException("Cannot find icon.jpg");
        }
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
    }

    public void setScene(Scene scene) {
        this.add(scene);
        this.pack();
        if (!isVisible) {
            this.setVisible(true);
        }
    }

    public void setController(PlayerController playerController) {
        this.add(playerController);
    }
//
//    public void paint(Graphics graphics) {
//        scene.paint(graphics);
//    }
//


}
