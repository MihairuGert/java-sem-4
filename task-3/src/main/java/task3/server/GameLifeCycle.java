package task3.server;

import task3.entity.Entity;
import task3.entity.Player;
import task3.server.commands.player.ControllerCommand;
import task3.server.commands.player.Movement;
import task3.view.Scene;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// TODO: Rename
public class GameLifeCycle implements ActionListener {
    Timer timer;
    ArrayList<Player> players = new ArrayList<>();
    Scene scene;

    public GameLifeCycle(Scene scene) {
        timer = new Timer(1, this);
        timer.start();
        this.scene = scene;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Player p : players) {
            Movement.execute(p, p.getInput());
            System.out.println(p.getX() + " " + p.getY());
        }
        scene.getPlayers(players);
        scene.repaint();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

}
