package task3.server;

import task3.entity.Entity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// TODO: Rename
public class GameLifeCycle implements ActionListener {
    Timer timer;
    ArrayList<Entity> entities = new ArrayList<>();

    GameLifeCycle() {
        timer = new Timer(100, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
