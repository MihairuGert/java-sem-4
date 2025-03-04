package task3.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class MultiplayerMenu extends Menu{

    private final JButton hostButton;
    private final JButton joinButton;
    private final JButton exitButton;

    public MultiplayerMenu(Dimension screenSize, MenuListener menuListener) {
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

        hostButton = addButton("/hostButton.png");
        this.add(hostButton);

        joinButton = addButton("/joinButton.png");
        this.add(joinButton);

        exitButton = addButton("/exitButton.png");
        this.add(exitButton);

        this.setComponentZOrder(background, this.getComponentCount() - 1);
        this.setComponentZOrder(hostButton, 0);
        this.setComponentZOrder(joinButton, 1);
        this.setComponentZOrder(exitButton, 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hostButton) {
            menuListener.host();
        }
        if (e.getSource() == joinButton) {
            menuListener.join();
        }
        if (e.getSource() == exitButton) {
            menuListener.goBack();
        }
    }
}
