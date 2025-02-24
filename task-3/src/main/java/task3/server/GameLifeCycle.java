package task3.server;

import task3.entity.Obstacle;
import task3.entity.Player;
import task3.server.commands.player.ControllerCommand;
import task3.server.commands.player.Movement;
import task3.view.Scene;

import javax.swing.*;
import java.awt.*;
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
        obstacles.add(new Obstacle(100,100,100,100));
        obstacles.add(new Obstacle(100,240,100,100));
        obstacles.add(new Obstacle(220,330/2,100,100));
        obstacles.add(new Obstacle(300,600,100,500));
        addBoundaries();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Player p : players) {
            Movement.execute(p, p.getInput());
            for (Obstacle o : obstacles) {
                Collision.handleCollision(p, o);
            }
            System.out.println(p.getX() + " " + p.getY());
        }
        scene.setPlayers(players);
        scene.setObstacles(obstacles);
        scene.repaint();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    private void addBoundaries() {
        Dimension dim = scene.getDimension();
        obstacles.add(new Obstacle(0,0,dim.width,10));
        obstacles.add(new Obstacle(dim.width - 20,0, 10, dim.height));
        obstacles.add(new Obstacle(0, dim.height - 100, dim.width, 10));
        obstacles.add(new Obstacle(0,0,10, dim.height));
    }

}
