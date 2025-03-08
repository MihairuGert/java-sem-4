package task3;

import task3.controller.PlayerController;
import task3.entity.Movable;
import task3.entity.Obstacle;
import task3.entity.Player;
import task3.network.Client;
import task3.network.Host;
import task3.network.SavedGame;
import task3.model.GameModel;
import task3.model.GameModelListener;
import task3.view.*;
import task3.controller.SystemConfig;

import java.awt.*;
import java.util.ArrayList;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game implements MainWindowListener,MenuListener, GameModelListener {
    private SystemConfig systemConfig;
    private MainWindow mainWindow;
    private GameMenu gameMenu;
    private MultiplayerMenu multiplayerMenu;
    private GameField gameField;
    private PlayerController playerController;
    GameModel gameModel;
    private Host host = null;
    private Client client = null;

    public void runGame() {
        systemConfig = new SystemConfig();
        mainWindow = new MainWindow(this);

        gameMenu = new GameMenu(systemConfig.getScreenSize(), this);
        multiplayerMenu = new MultiplayerMenu(systemConfig.getScreenSize(), this);
        mainWindow.setScene(gameMenu);
    }

    private void continueGame() {
        mainWindow.setScene(gameMenu);
    }

    @Override
    public void startSingleplayer() {
        try {
            initializeGame();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return;
        }
        gameModel = new GameModel(this);
        Player player = new Player(playerController);
        gameModel.addPlayer(player);
    }

    private void initializeGame() {
        mainWindow.removeScene(gameMenu);
        mainWindow.removeScene(multiplayerMenu);

        try {
            gameField = new GameField(systemConfig.getScreenSize());
        } catch (RuntimeException e) {
            throw new RuntimeException("Cannot initialize game: " + e.getMessage());
        }

        mainWindow.setScene(gameField);

        playerController = new PlayerController(systemConfig.getScreenSize());
        mainWindow.setController(playerController);
        playerController.setFocusable(true);
        playerController.requestFocusInWindow();
    }

    @Override
    public void startMultiplayerMenu() {
        mainWindow.removeScene(gameMenu);
        if (gameField != null)
            mainWindow.removeScene(gameField);
        mainWindow.setScene(multiplayerMenu);
    }

    private void hostGame() {
        try {
            initializeGame();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return;
        }
        GameModel gameModel = new GameModel(this);

        Player player = new Player(playerController);
        gameModel.addPlayer(player);

        host = new Host(gameModel);
    }

    private void joinGame() {
        String ip = multiplayerMenu.writeHostIp();
        try {
            client = new Client(ip);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        try {
            initializeGame();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return;
        }
        Player player = new Player(playerController);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                client.sendPlayerInputInfo(player);
            } catch (RuntimeException e) {
                scheduler.shutdown();
                endGame();
                return;
            }
            }, 0, 6, TimeUnit.MILLISECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            SavedGame savedGame = null;
            try {
                savedGame = client.receiveGameData();
            } catch (RuntimeException e) {
                scheduler.shutdown();
                endGame();
                return;
            }
            if (savedGame != null) {
                ArrayList<Movable> movables = savedGame.getMovables();
                ArrayList<Obstacle> obstacles = savedGame.getObstacles();
                update(movables, obstacles);
            }
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
        if (host != null) {
            host.closeConnection();
        }
        continueGame();
    }

    @Override
    public Dimension getScreenSize() {
        return systemConfig.getScreenSize();
    }

    @Override
    public void update(ArrayList<Movable> movables, ArrayList<Obstacle> obstacles) {
        if (host != null) {
            try {
                host.sendUpdate(new SavedGame(obstacles, movables));
            } catch (RuntimeException ignored) {}
        }
        gameField.setPlayers(movables);
        gameField.setObstacles(obstacles);
        gameField.repaint();
    }

    @Override
    public void mainWindowExit() {
        try {
            if (client != null) {
                client.closeConnection();
            }
            else if (host != null) {
                host.closeConnection();
            }
        } catch (RuntimeException e) {
            System.err.println("Cannot close connection: " + e.getMessage());
        }
    }
}
