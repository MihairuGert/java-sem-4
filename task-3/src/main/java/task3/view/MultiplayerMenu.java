package task3.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class MultiplayerMenu extends Menu {

    private final JButton hostButton;
    private final JButton joinButton;
    private final JButton exitButton;
    private ConfirmButton confirmButton;

    private JTextField ipInputField = null;

    private class ConfirmButton extends JButton implements ActionListener {
        private String ipAddress;

        public ConfirmButton(String text) {
            super(text);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ipAddress = ipInputField.getText();
            menuListener.join();
        }
    }

    public MultiplayerMenu(Dimension screenSize, MenuListener menuListener) {
        super(screenSize, menuListener);
        URL iconURL = getClass().getResource("/gameName.png");
        ImageIcon titleIcon = null;
        if (iconURL != null) {
            Image image = new ImageIcon(iconURL).getImage().getScaledInstance(screenSize.width / 3, screenSize.height / 3, Image.SCALE_DEFAULT);
            titleIcon = new ImageIcon(image);
        }
        JLabel title = new JLabel(titleIcon);
        title.setBounds(xOffset, 0, screenSize.width / 3, screenSize.height / 3);
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
            prepareIpInput();
        }
        if (e.getSource() == exitButton) {
            menuListener.goBack();
        }
    }

    private void prepareIpInput() {
        ipInputField = new JTextField();
        ipInputField.setBounds(joinButton.getX() + xOffset * 5, joinButton.getY() + 50, 200, 30);
        ipInputField.setFont(new Font("Arial", Font.PLAIN, 24));
        this.add(ipInputField);

        confirmButton = new ConfirmButton("Accept");
        confirmButton.addActionListener(confirmButton);
        confirmButton.setBounds(ipInputField.getX(), ipInputField.getY() + 40, 200, 30);
        this.add(confirmButton);

        this.setComponentZOrder(background, this.getComponentCount() - 1);
        this.setComponentZOrder(ipInputField, 0);
        this.setComponentZOrder(confirmButton, 1);
        this.setComponentZOrder(hostButton, 2);
        this.setComponentZOrder(joinButton, 3);
        this.setComponentZOrder(exitButton, 4);

        this.revalidate();
        this.repaint();
    }

    public String writeHostIp() {
        return confirmButton.ipAddress;
    }
}
