package task3.server;

import task3.entity.Obstacle;
import task3.entity.Player;
import task3.server.commands.player.ControllerCommand;
import task3.server.commands.player.Movement;
import task3.view.Scene;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

// TODO: Rename
public class GameLifeCycle implements ActionListener {
    Timer timer;
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    Scene scene;

    public GameLifeCycle(Scene scene) {
        timer = new Timer(1, this);
        timer.start();
        // TODO: REMOVE OR MAKE AN ARRAY LIST
        this.scene = scene;
        obstacles.add(new Obstacle(300,0,100,500));
        obstacles.add(new Obstacle(300,600,100,500));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Player p : players) {
            LinkedList<ControllerCommand> playerInput = new LinkedList<>();
            for (Obstacle obstacle : obstacles) {
                // change the player input deleting what you don't need
                // TODO
                if ()
            }
            Movement.execute(p, p.getInput());
            // DEBUG
            System.out.println(p.getX() + " " + p.getY());
        }
        scene.setPlayers(players);
        scene.setObstacles(obstacles);
        scene.repaint();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

}
