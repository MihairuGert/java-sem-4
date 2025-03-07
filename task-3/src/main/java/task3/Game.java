package task3;

import task3.controller.PlayerController;
import task3.entity.Movable;
import task3.entity.Obstacle;
import task3.entity.Player;
import task3.network.Client;
import task3.network.Host;
import task3.network.SavedGame;
import task3.server.GameModel;
import task3.server.GameModelListener;
import task3.view.*;
import task3.controller.SystemConfig;

import java.awt.*;
import java.util.ArrayList;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game implements MenuListener, GameModelListener {
    private SystemConfig systemConfig;
    private MainWindow mainWindow;
    private GameMenu gameMenu;
    private MultiplayerMenu multiplayerMenu;
    private GameField gameField;
    private PlayerController playerController;
    private Host host = null;

    public void runGame() {
        systemConfig = new SystemConfig();
        mainWindow = new MainWindow();

        gameMenu = new GameMenu(systemConfig.getScreenSize(), this);
        multiplayerMenu = new MultiplayerMenu(systemConfig.getScreenSize(), this);
        mainWindow.setScene(gameMenu);
    }

    private void continueGame() {
        mainWindow.setScene(gameMenu);
    }

    @Override
    public void startSingleplayer() {
        initializeGame();
    }

    private void initializeGame() {
        mainWindow.removeScene(gameMenu);
        mainWindow.removeScene(multiplayerMenu);

        gameField = new GameField(systemConfig.getScreenSize());
        mainWindow.setScene(gameField);

        playerController = new PlayerController(systemConfig.getScreenSize());
        mainWindow.setController(playerController);
        playerController.setFocusable(true);
        playerController.requestFocusInWindow();
    }

    @Override
    public void startMultiplayerMenu() {
        mainWindow.remove(gameMenu);
        mainWindow.setScene(multiplayerMenu);
    }

    private void hostGame() {
        initializeGame();
        GameModel gameModel = new GameModel(this);

        Player player = new Player(playerController);
        gameModel.addPlayer(player);

        host = new Host(gameModel);
    }

    private void joinGame() {
        String ip = multiplayerMenu.writeHostIp();
        Client client;
        try {
            client = new Client(ip);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        initializeGame();
        Player player = new Player(playerController);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        scheduler.scheduleAtFixedRate(() -> client.sendPlayerInputInfo(player), 0, 6, TimeUnit.MILLISECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            SavedGame savedGame = client.receiveGameData();
            ArrayList<Movable> movables = savedGame.getMovables();
            ArrayList<Obstacle> obstacles = savedGame.getObstacles();
            update(movables, obstacles);
        }, 0, 6, TimeUnit.MILLISECONDS);
    }

    @Override
    public void exit() {
        mainWindow.dispose();
    }

    @Override
    public void goBack() {
        mainWindow.remove(multiplayerMenu);
        mainWindow.setScene(gameMenu);
    }

    @Override
    public void host() {
        hostGame();
    }

    @Override
    public void join() {
        joinGame();
    }

    @Override
    public void endGame() {
        mainWindow.removeScene(gameField);
        mainWindow.remove(playerController);
        continueGame();
    }

    @Override
    public Dimension getScreenSize() {
        return systemConfig.getScreenSize();
    }

    @Override
    public void update(ArrayList<Movable> movables, ArrayList<Obstacle> obstacles) {
        if (host != null) {
            host.sendUpdate(new SavedGame(obstacles, movables));
        }
        gameField.setPlayers(movables);
        gameField.setObstacles(obstacles);
        gameField.repaint();
    }
}
