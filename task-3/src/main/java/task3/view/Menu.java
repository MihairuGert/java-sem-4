package task3.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends Scene implements ActionListener {

    private JButton singlePlayerButton;
    private JButton multiPlayerButton;
    private JButton exitButton;
    private MenuListener menuListener;

    public Menu(Dimension screenSize, MenuListener menuListener) {
        super(screenSize);
        this.setLayout(null);
        this.menuListener = menuListener;

        singlePlayerButton = new JButton("Single player");
        singlePlayerButton.setBounds(50,100,200, 100);
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
