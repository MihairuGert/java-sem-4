package task3.server;

import task3.entity.Entity;
import task3.entity.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// TODO: Rename
public class GameLifeCycle implements ActionListener {
    Timer timer;
    ArrayList<Player> players = new ArrayList<>();

    GameLifeCycle() {
        timer = new Timer(100, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Player p : players) {
            p.getInput();
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

}
