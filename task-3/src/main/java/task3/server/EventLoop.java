package task3.server;

import task3.controller.AIController;
import task3.entity.Entity;
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
import java.util.LinkedList;

// TODO: Rename
public class EventLoop implements ActionListener {
    Timer timer;

    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Obstacle> obstacles = new ArrayList<>();

    Scene scene;

    public EventLoop(Scene scene) {
        timer = new Timer(10, this);
        // TODO: REMOVE OR MAKE AN ARRAY LIST
        // TODO: SEPARATE PLAYERS AND UNDEAD
        this.scene = scene;
        obstacles.add(new Obstacle(100,100,100,100));
        obstacles.add(new Obstacle(100,240,100,100));
        obstacles.add(new Obstacle(220,330/2,100,100));
        obstacles.add(new Obstacle(300,600,100,500));
        addBoundaries();

        for (int i = 0; i < 30; i++) {
            Undead boba = new Undead(new AIController());
            players.add(boba);
            boba.move(300+(int)(Math.random()*1000)%500, 100+(int)(Math.random()*1000)%500);
        }
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LinkedList<Entity> entities = new LinkedList<>();
        entities.addAll(players);
        entities.addAll(obstacles);
        for (Player p : players) {
            Movement.execute(p, p.getInput());
            Point point = p.getMousePoint();
            for (Entity entity : entities) {
                if (p != entity) {
                    Collision.handleCollision(p, entity);
                    //if (p.getClass().getName().equals("task3.entity.Player"))
                        p.tryAttack(point, entity);
                }
            }
            Entity entity = p.whoWasKilled();
            if (entity != null) {
                entity.kill();
            }
        }
        players.removeIf(Player::isDead);
        // TODO ADD HERE VISION
        // ADD AWT EVENT THREAD SO VIEW BE INDEPENDENT
        scene.setPlayers(players);
        scene.setObstacles(obstacles);
        scene.repaint();
    }

    public void addPlayer(Player player) {
        players.add(player);
        for (Player undead : players) {
            if (undead.getClass().getName().equals("task3.entity.Undead")) {
                ((Undead) undead).setEntityToChase(player);
            }
        }
    }

    private void addBoundaries() {
        Dimension dim = scene.getDimension();
        obstacles.add(new Obstacle(0,0,dim.width,30));
        obstacles.add(new Obstacle(dim.width - 20,0, 30, dim.height));
        obstacles.add(new Obstacle(0, dim.height - 100, dim.width, 30));
        obstacles.add(new Obstacle(0,0,30, dim.height));
    }

}
