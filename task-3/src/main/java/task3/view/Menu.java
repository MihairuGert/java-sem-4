package task3.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Menu extends Scene implements ActionListener {

    private final JButton singlePlayerButton;
    private final JButton multiPlayerButton;
    private final JButton exitButton;
    private final MenuListener menuListener;

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

        URL iconURL = getClass().getResource("/menuBackground.png");
        ImageIcon backGroundImage = null;
        if (iconURL != null) {
            Image image = new ImageIcon(iconURL).getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_DEFAULT);
            backGroundImage = new ImageIcon(image);
        }

        this.setLayout(null);
        this.setOpaque(true);
        this.menuListener = menuListener;

        JLabel background = new JLabel(backGroundImage);
        background.setBounds(0,0, screenSize.width, screenSize.height);
        this.add(background);

        iconURL = getClass().getResource("/gameName.png");
        ImageIcon titleIcon = null;
        if (iconURL != null) {
            Image image = new ImageIcon(iconURL).getImage().getScaledInstance(screenSize.width / 3, screenSize.height / 3, Image.SCALE_DEFAULT);
            titleIcon = new ImageIcon(image);
        }
        JLabel title = new JLabel(titleIcon);
        title.setBounds(xOffset,0, screenSize.width / 3, screenSize.height / 3);
        this.add(title);

        singlePlayerButton = addButton("/singleplayerButton.png");
        this.add(singlePlayerButton);

        multiPlayerButton = addButton("/multiplayerButton.png");
        this.add(multiPlayerButton);

        exitButton = addButton("/exitButton.png");
        this.add(exitButton);

        this.setComponentZOrder(background, this.getComponentCount() - 1);
        this.setComponentZOrder(singlePlayerButton, 0);
        this.setComponentZOrder(multiPlayerButton, 1);
        this.setComponentZOrder(exitButton, 2);

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
