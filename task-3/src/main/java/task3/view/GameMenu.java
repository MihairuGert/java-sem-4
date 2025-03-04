package task3.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class GameMenu extends Menu{

    private final JButton singlePlayerButton;
    private final JButton multiPlayerButton;
    private final JButton exitButton;

    public GameMenu(Dimension screenSize, MenuListener menuListener) {
        super(screenSize, menuListener);
        URL iconURL = getClass().getResource("/gameName.png");
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
        else if (e.getSource() == multiPlayerButton) {
            menuListener.startMultiplayer();
        }
        else if (e.getSource() == exitButton) {
            menuListener.exit();
        }
    }

}
