package task3.view;

import task3.Game;
import task3.controller.PlayerController;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;

public class MainWindow extends JFrame {
    private boolean isVisible = false;
    private MainWindowListener mainWindowListener;

    public MainWindow(MainWindowListener mainWindowListener) {
        this.mainWindowListener = mainWindowListener;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setTitle("Shooter the game");

        URL iconURL = getClass().getResource("/icon.jpg");
        if (iconURL == null) {
            throw new RuntimeException("Cannot find icon.jpg");
        }
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                mainWindowListener.mainWindowExit();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public void setScene(Scene scene) {
        this.add(scene);
        this.pack();
        if (!isVisible) {
            this.setVisible(true);
            isVisible = true;
        }
        this.revalidate();
        this.repaint();
    }

    public void removeScene(Scene scene) {
        this.remove(scene);
        this.revalidate();
        this.repaint();
    }

    public void setController(PlayerController playerController) {
        this.add(playerController);
    }
}
