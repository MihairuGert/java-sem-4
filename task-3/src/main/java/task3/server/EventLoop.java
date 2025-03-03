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

    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Movable> movables = new ArrayList<>();
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    EventLoopListener eventLoopListener;

    GameField gameField;

    public EventLoop(GameField gameField, EventLoopListener eventLoopListener) {
        timer = new Timer(10, this);
        // TODO: REMOVE OR MAKE AN ARRAY LIST
        this.gameField = gameField;
        addBoundaries();
        createMap();
        for (int i = 0; i < 30; i++) {
            Undead boba = new Undead(new AIController());
            movables.add(boba);
            boba.move(300+(int)(Math.random()*1000)%500, 100+(int)(Math.random()*1000)%500);
        }

        this.eventLoopListener = eventLoopListener;

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
        players.removeIf(Player::isDead);

        if (arePlayersDead()) {
            timer.stop();
            eventLoopListener.endGame();
        }

        // ADD AWT EVENT THREAD SO VIEW BE INDEPENDENT

        gameField.setPlayers(movables);
        gameField.setObstacles(obstacles);
        gameField.repaint();
    }

    private boolean arePlayersDead() {
        return players.isEmpty();
    }

    public void addPlayer(Player player) {
        movables.add(player);
        players.add(player);
        for (Movable undead : movables) {
            if (undead.getClass().getName().equals("task3.entity.Undead")) {
                ((Undead) undead).setEntityToChase(player);
            }
        }
    }

    private void addBoundaries() {
        Dimension dim = gameField.getDimension();
        obstacles.add(new Obstacle(-20,-20, dim.width,30));
        obstacles.add(new Obstacle(dim.width - 20,0, 30, dim.height));
        obstacles.add(new Obstacle(-20, dim.height - 100, dim.width, 30));
        obstacles.add(new Obstacle(-20,-20,30, dim.height));
    }

    private void createDebugMap() {
        obstacles.add(new Obstacle(100,100,100,100));
        obstacles.add(new Obstacle(100,240,100,100));
        obstacles.add(new Obstacle(220,330/2,100,100));
        obstacles.add(new Obstacle(300,600,100,500));
    }

    private void createMap() {
        Dimension dim = gameField.getDimension();

        obstacles.add(new Obstacle(dim.width - 120, 0, 50, dim.height / 3));
        obstacles.add(new Obstacle(dim.width - 400, 0, 50, dim.height / 4));
        obstacles.add(new Obstacle(dim.width - 400, dim.height / 3 - 50, 280, 50));
        obstacles.add(new Obstacle(dim.width - 120, dim.height / 3 + 50, 50, dim.height / 2));
        obstacles.add(new Obstacle(dim.width - 200, dim.height / 3 + 50, 50, dim.height / 2));
        obstacles.add(new Obstacle(dim.width - 280, dim.height / 3 + 50, 50, dim.height / 2));

        obstacles.add(new Obstacle(50, 0, 40, dim.height / 2));
        obstacles.add(new Obstacle(50, dim.height / 2 - 100, 180, 40));
        obstacles.add(new Obstacle(200, dim.height / 2 - 100, 40, 180));
        obstacles.add(new Obstacle(100, dim.height / 2 + 50, 20, 100));
        obstacles.add(new Obstacle(150, dim.height / 2 + 50, 20, 100));

        obstacles.add(new Obstacle(dim.width / 2 - 120, dim.height / 2 - 60, 100, 100));
        obstacles.add(new Obstacle(dim.width / 2 + 20, dim.height / 2 - 60, 100, 100));
        obstacles.add(new Obstacle(dim.width / 2 - 60, dim.height / 2 + 60, 120, 20));


        obstacles.add(new Obstacle(dim.width - 500, dim.height - 150, 150, 40));
        obstacles.add(new Obstacle(dim.width - 550, dim.height / 2, 40, 120));
        obstacles.add(new Obstacle(dim.width - 600, dim.height / 2 + 100, 20, 100));
        obstacles.add(new Obstacle(dim.width - 450, dim.height / 2 + 100, 20, 100));

        obstacles.add(new Obstacle(dim.width / 2 - 200, 100, 400, 30));
        obstacles.add(new Obstacle(dim.width / 2 - 200, dim.height - 240, 400, 30));

        obstacles.add(new Obstacle(300, 200, 60, 60));
        obstacles.add(new Obstacle(dim.width - 360, dim.height - 200, 60, 60));

        obstacles.add(new Obstacle(400, dim.height - 100, 20, 100));
        obstacles.add(new Obstacle(500, dim.height - 200, 20, 100));
        obstacles.add(new Obstacle(600, dim.height - 250, 20, 100));

        obstacles.add(new Obstacle(50, dim.height - 350, 200, 20));
        obstacles.add(new Obstacle(150, dim.height - 300, 20, 100));
        obstacles.add(new Obstacle(250, dim.height - 250, 20, 100));
        obstacles.add(new Obstacle(300, dim.height - 200, 100, 20));
    }
}
