package task3.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Menu extends Scene implements ActionListener {

    private JButton singlePlayerButton;
    private JButton multiPlayerButton;
    private JButton exitButton;
    private MenuListener menuListener;

    private final int buttonHeight = 150;
    private final int buttonWidth = 300;
    private final int xOffset = 60;
    private final int yOffset = 100;
    private final int rangeBetweenButtons = 25;
    private int buttonsNum = 0;

    private JButton addButton(String path) {
        buttonsNum++;
        URL iconURL = getClass().getResource(path);
        Icon texture = null;
        if (iconURL != null) {
            Image image = new ImageIcon(iconURL).getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT);
            texture = new ImageIcon(image);
        }
        JButton button = new JButton(texture);
        button.setBounds(xOffset,yOffset + (buttonHeight + rangeBetweenButtons) * buttonsNum, buttonWidth, buttonHeight);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;
    }

    public Menu(Dimension screenSize, MenuListener menuListener) {
        super(screenSize);
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(Color.lightGray);
        this.menuListener = menuListener;

        singlePlayerButton = addButton("/singleplayerButton.png");
        this.add(singlePlayerButton);

        multiPlayerButton = addButton("/multiplayerButton.png");
        this.add(multiPlayerButton);

        exitButton = addButton("/exitButton.png");
        this.add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == singlePlayerButton) {
            menuListener.startSingleplayer();
        }
        else if (e.getSource() == exitButton) {
            menuListener.exit();
        }
    }
}
