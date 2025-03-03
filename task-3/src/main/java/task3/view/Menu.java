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

    public Menu(Dimension screenSize, MenuListener menuListener) {
        super(screenSize);
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(Color.lightGray);
        this.menuListener = menuListener;

        URL iconURL = getClass().getResource("/singleplayerButton.png");
        Icon texture = null;
        if (iconURL != null)
            texture = new ImageIcon(iconURL);
        singlePlayerButton = new JButton(texture);
        singlePlayerButton.setBounds(50,100,200, 100);
        singlePlayerButton.setBorderPainted(false);
        singlePlayerButton.setContentAreaFilled(false);
        singlePlayerButton.setFocusPainted(false);
        singlePlayerButton.addActionListener(this);
        this.add(singlePlayerButton);

        multiPlayerButton = new JButton("Multiplayer");
        multiPlayerButton.setBounds(50,100 + 200,200, 100);
        multiPlayerButton.addActionListener(this);
        this.add(multiPlayerButton);

        exitButton = new JButton("Exit game");
        exitButton.setBounds(50,100 + 200 * 2,200, 100);
        exitButton.addActionListener(this);
        this.add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == singlePlayerButton) {
            menuListener.startSingleplayer();
        }
    }
}
