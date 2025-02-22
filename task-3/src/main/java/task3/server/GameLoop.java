package task3.server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: Rename
public class GameLoop implements ActionListener {
    Timer timer;
    GameLoop() {
        timer = new Timer(100, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
