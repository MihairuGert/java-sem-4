package task3.server;

import task3.controller.AIController;
import task3.entity.*;
import task3.server.commands.player.Movement;
import task3.sound.SoundPlayer;
import task3.view.GameField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

// TODO: Rename
public class EventLoop implements ActionListener {
    Timer timer;

    ArrayList<Movable> movables = new ArrayList<>();
    ArrayList<Obstacle> obstacles = new ArrayList<>();

    GameField gameField;

    public EventLoop(GameField gameField) {
        timer = new Timer(10, this);
        // TODO: REMOVE OR MAKE AN ARRAY LIST
        // TODO: SEPARATE PLAYERS AND UNDEAD
        this.gameField = gameField;
        obstacles.add(new Obstacle(100,100,100,100));
        obstacles.add(new Obstacle(100,240,100,100));
        obstacles.add(new Obstacle(220,330/2,100,100));
        obstacles.add(new Obstacle(300,600,100,500));
        addBoundaries();

        for (int i = 0; i < 30; i++) {
            Undead boba = new Undead(new AIController());
            movables.add(boba);
            boba.move(300+(int)(Math.random()*1000)%500, 100+(int)(Math.random()*1000)%500);
        }
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LinkedList<Entity> entities = new LinkedList<>();
        entities.addAll(movables);
        entities.addAll(obstacles);
        for (Movable p : movables) {
            Movement.execute(p, p.getInput());
            Point point = p.getMousePoint();
            if (point != null && p.getClass().getName().equals("task3.entity.Player")) {
                SoundPlayer.playShoot();
            }
            for (Entity entity : entities) {
                if (p != entity) {
                    Collision.handleCollision(p, entity);
                    p.tryAttack(point, entity);
                }
            }
            Entity entity = p.whoWasKilled();
            if (entity != null) {
                entity.kill();
            }
        }
        movables.removeIf(Movable::isDead);
        // ADD AWT EVENT THREAD SO VIEW BE INDEPENDENT
        gameField.setPlayers(movables);
        gameField.setObstacles(obstacles);
        gameField.repaint();
    }

    public void addPlayer(Player player) {
        movables.add(player);
        for (Movable undead : movables) {
            if (undead.getClass().getName().equals("task3.entity.Undead")) {
                ((Undead) undead).setEntityToChase(player);
            }
        }
    }

    private void addBoundaries() {
        Dimension dim = gameField.getDimension();
        obstacles.add(new Obstacle(0,0,dim.width,30));
        obstacles.add(new Obstacle(dim.width - 20,0, 30, dim.height));
        obstacles.add(new Obstacle(0, dim.height - 100, dim.width, 30));
        obstacles.add(new Obstacle(0,0,30, dim.height));
    }

}
