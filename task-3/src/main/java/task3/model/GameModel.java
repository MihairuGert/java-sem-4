package task3.model;

import task3.entity.*;
import task3.engine.EventLoop;
import task3.engine.EventLoopListener;
import task3.engine.WaveGenerator;
import task3.network.ClientHandler;
import task3.network.HostListener;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameModel implements EventLoopListener, HostListener {
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Movable> movables = new ArrayList<>();
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private final ArrayList<GameModelListener> gameModelListeners = new ArrayList<>();

    private final WaveGenerator waveGenerator = new WaveGenerator(30);
    private final EventLoop eventLoop;

    public ArrayList<Movable> getMovables() {
        return movables;
    }
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public GameModel(GameModelListener gameModelListener, int width, int height) {
        addGameModelListener(gameModelListener);
        addBoundaries(width, height);
        createMap(width, height);
        eventLoop = new EventLoop(this);
    }
    public void addGameModelListener(GameModelListener gameModelListener) {
        gameModelListeners.add(gameModelListener);
    }

    private void spawnEnemy(String className, int number) {
        for (int i = 0; i < number; i++) {
            Undead undead;
            try {
                undead = (Undead) Class.forName(className).getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                System.err.println(e.getMessage());
                continue;
            }
            movables.add(undead);
            undead.move(100 + (int)(Math.random() * 1500) % 1000, 100 + (int)(Math.random() * 1500) % 1000);
            undead.setEntityToChase(players.get((int)(Math.random() * 10) % players.size()));
        }
    }

    private void addBoundaries(int width, int height) {
        obstacles.add(new Obstacle(-20,-20, width,30));
        obstacles.add(new Obstacle(width - 20,0, 30, height));
        obstacles.add(new Obstacle(-20, height - 100, width, 30));
        obstacles.add(new Obstacle(-20,-20,30, height));
    }

    private void createMap(int width, int height) {
        obstacles.add(new Obstacle(width - 120, 0, 50, height / 3));
        obstacles.add(new Obstacle(width - 400, 0, 50, height / 4));
        obstacles.add(new Obstacle(width - 400, height / 3 - 50, 280, 50));
        obstacles.add(new Obstacle(width - 120, height / 3 + 50, 50, height / 2));
        obstacles.add(new Obstacle(width - 200, height / 3 + 50, 50, height / 2));
        obstacles.add(new Obstacle(width - 280, height / 3 + 50, 50, height / 2));

        obstacles.add(new Obstacle(50, 0, 40, height / 2));
        obstacles.add(new Obstacle(50, height / 2 - 100, 180, 40));
        obstacles.add(new Obstacle(200, height / 2 - 100, 40, 180));
        obstacles.add(new Obstacle(100, height / 2 + 50, 20, 100));
        obstacles.add(new Obstacle(150, height / 2 + 50, 20, 100));

        obstacles.add(new Obstacle(width / 2 - 120, height / 2 - 60, 100, 100));
        obstacles.add(new Obstacle(width / 2 + 20, height / 2 - 60, 100, 100));
        obstacles.add(new Obstacle(width / 2 - 60, height / 2 + 60, 120, 20));

        obstacles.add(new Obstacle(width - 500, height - 150, 150, 40));
        obstacles.add(new Obstacle(width - 550, height / 2, 40, 120));
        obstacles.add(new Obstacle(width - 600, height / 2 + 100, 20, 100));
        obstacles.add(new Obstacle(width - 450, height / 2 + 100, 20, 100));

        obstacles.add(new Obstacle(width / 2 - 200, 100, 400, 30));
        obstacles.add(new Obstacle(width / 2 - 200, height - 240, 400, 30));

        obstacles.add(new Obstacle(300, 200, 60, 60));
        obstacles.add(new Obstacle(width - 360, height - 200, 60, 60));

        obstacles.add(new Obstacle(400, height - 100, 20, 100));
        obstacles.add(new Obstacle(500, height - 200, 20, 100));
        obstacles.add(new Obstacle(600, height - 250, 20, 100));

        obstacles.add(new Obstacle(50, height - 350, 200, 20));
        obstacles.add(new Obstacle(150, height - 300, 20, 100));
        obstacles.add(new Obstacle(250, height - 250, 20, 100));
        obstacles.add(new Obstacle(300, height - 200, 100, 20));
    }
    public void removeDead() {
        movables.removeIf(Movable::isDead);
        players.removeIf(Player::isDead);
    }
    public void update(ArrayList<String> sounds) {
        for (GameModelListener gameModelListener : gameModelListeners) {
            gameModelListener.update(movables, obstacles, sounds);
        }
    }

    public boolean arePlayersDead() {
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

    @Override
    public void newClient(ClientHandler clientHandler) {
        Player player = new Player();
        clientHandler.setPlayer(player);
        movables.add(player);
        players.add(player);
    }

    public void nextWave() {
        HashMap<String, Integer> nextWave = waveGenerator.calculateNextWave();
        nextWave.forEach(this::spawnEnemy);
    }
}
