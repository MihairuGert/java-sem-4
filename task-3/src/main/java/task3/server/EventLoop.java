package task3.server;

import task3.controller.AIController;
import task3.entity.Obstacle;
import task3.entity.Player;
import task3.entity.Undead;
import task3.server.commands.player.Movement;
import task3.view.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// TODO: Rename
public class EventLoop implements ActionListener {
    Timer timer;
    ArrayList<Undead> undeads = new ArrayList<>();
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    Scene scene;

    public EventLoop(Scene scene) {
        timer = new Timer(1, this);
        // TODO: REMOVE OR MAKE AN ARRAY LIST
        this.scene = scene;
        obstacles.add(new Obstacle(100,100,100,100));
        obstacles.add(new Obstacle(100,240,100,100));
        obstacles.add(new Obstacle(220,330/2,100,100));
        obstacles.add(new Obstacle(300,600,100,500));
        addBoundaries();

        for (int i = 0; i < 5; i++) {
            Undead boba = new Undead(new AIController());
            players.add(boba);
            undeads.add(boba);
            boba.move(100+(int)(Math.random()*1000)%500, 100+(int)(Math.random()*1000)%500);
        }
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Player p : players) {
            Movement.execute(p, p.getInput());
            for (Obstacle o : obstacles) {
                Collision.handleCollision(p, o);
            }
            for (Player player : players) {
                if (p != player) {
                    Collision.handleCollision(p, player);
                }
            }
        }
        // TODO ADD HERE VISION
        scene.setPlayers(players);
        scene.setObstacles(obstacles);
        scene.repaint();
    }

    public void addPlayer(Player player) {
        players.add(player);
        for (Undead undead : undeads) {
            undead.setEntityToChase(player);
        }
    }

    private void addBoundaries() {
        Dimension dim = scene.getDimension();
        obstacles.add(new Obstacle(0,0,dim.width,10));
        obstacles.add(new Obstacle(dim.width - 20,0, 10, dim.height));
        obstacles.add(new Obstacle(0, dim.height - 100, dim.width, 10));
        obstacles.add(new Obstacle(0,0,10, dim.height));
    }

}
