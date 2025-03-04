package task3.server;

import task3.controller.AIController;
import task3.entity.Movable;
import task3.entity.Obstacle;
import task3.entity.Player;
import task3.entity.Undead;
import task3.view.GameField;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameModel implements EventLoopListener {
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Movable> movables = new ArrayList<>();
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private final LinkedList<GameModelListener> gameModelListeners = new LinkedList<>();
    private final GameField gameField;
    private EventLoop eventLoop;

    ArrayList<Player> getPlayers() {
        return players;
    }
    ArrayList<Movable> getMovables() {
        return movables;
    }
    ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
    public GameModel(GameModelListener gameModelListener, GameField gameField) {
        this.gameField = gameField;
        addBoundaries();
        createMap();
        spawnZombie();
        eventLoop = new EventLoop(this);
        addGameModelListener(gameModelListener);
    }
    public void addGameModelListener(GameModelListener gameModelListener) {
        gameModelListeners.add(gameModelListener);
    }
    private void spawnZombie() {
        for (int i = 0; i < 30; i++) {
            Undead boba = new Undead(new AIController());
            movables.add(boba);
            boba.move(300+(int)(Math.random()*1000)%500, 100+(int)(Math.random()*1000)%500);
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
    void removeDead() {
        movables.removeIf(Movable::isDead);
        players.removeIf(Player::isDead);
    }
    void update() {
        gameField.setPlayers(movables);
        gameField.setObstacles(obstacles);
        gameField.repaint();
    }

    boolean arePlayersDead() {
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

    @Override
    public void endGame() {
        for (GameModelListener gameModelListener : gameModelListeners) {
            gameModelListener.endGame();
        }
    }
}
