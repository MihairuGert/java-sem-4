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
import task3.sound.Sound;
import task3.view.*;
import task3.controller.SystemConfig;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
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
    private Player player;

    GameModel gameModel;
    private Host host = null;
    private Client client = null;

    public void runGame() {
        systemConfig = new SystemConfig();
        mainWindow = new MainWindow(this);
        gameMenu = new GameMenu(systemConfig.getScreenSize(), this);
        multiplayerMenu = new MultiplayerMenu(systemConfig.getScreenSize(), this);
        SwingUtilities.invokeLater(() -> {
            mainWindow.setScene(gameMenu);
        });
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
        gameModel = new GameModel(this, systemConfig.getScreenSize().width, systemConfig.getScreenSize().height);
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
        SwingUtilities.invokeLater(() -> {
                    mainWindow.setScene(gameField);
                });
        player = new Player();
        playerController = new PlayerController(systemConfig.getScreenSize(), player);
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
        GameModel gameModel = new GameModel(this, systemConfig.getScreenSize().width, systemConfig.getScreenSize().height);
        gameModel.addPlayer(player);

        host = new Host(gameModel);
    }

    private void runConnection() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                client.sendPlayerData(player);
            } catch (RuntimeException e) {
                SwingUtilities.invokeLater(this::endGame);
                scheduler.shutdown();
                return;
            }
        }, 0, 6, TimeUnit.MILLISECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            SavedGame savedGame;
            try {
                savedGame = client.receiveSavedData();
            } catch (RuntimeException e) {
                SwingUtilities.invokeLater(this::endGame);
                scheduler.shutdown();
                return;
            }
            if (savedGame != null) {
                ArrayList<Movable> movables = savedGame.getMovables();
                ArrayList<Obstacle> obstacles = savedGame.getObstacles();
                ArrayList<String> sounds = savedGame.getSounds();
                SwingUtilities.invokeLater(() -> update(movables, obstacles, sounds));
            }
        }, 0, 6, TimeUnit.MILLISECONDS);
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
        runConnection();
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
    public void update(ArrayList<Movable> movables, ArrayList<Obstacle> obstacles, ArrayList<String> sounds) {
        if (host != null) {
            try {
                host.sendUpdate(new SavedGame(obstacles, movables, sounds));
            } catch (RuntimeException ignored) {}
        }
        for (String soundName : sounds) {
            try {
                Sound sound = (Sound) Class.forName(soundName).getDeclaredConstructor().newInstance();
                sound.playSound();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
        SwingUtilities.invokeLater(() -> {
            gameField.setPlayers(movables);
            gameField.setObstacles(obstacles);
            gameField.repaint();
        });
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
