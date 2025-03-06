package task3;

import task3.controller.PlayerController;
import task3.entity.Entity;
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
import task3.view.Menu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Game implements MenuListener, GameModelListener {
    private SystemConfig systemConfig;
    private MainWindow mainWindow;
    private GameMenu gameMenu;
    private MultiplayerMenu multiplayerMenu;
    private GameField gameField;
    private PlayerController playerController;
    private GameModel gameModel;
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
        gameField = new GameField(systemConfig.getScreenSize());

        mainWindow.setScene(gameField);

        playerController = new PlayerController(systemConfig.getScreenSize());
        mainWindow.setController(playerController);

        playerController.setFocusable(true);
        playerController.requestFocusInWindow();

        Player player = new Player(playerController);
        gameModel = new GameModel(this);
        gameModel.addPlayer(player);
    }

    @Override
    public void startMultiplayerMenu() {
        mainWindow.remove(gameMenu);
        mainWindow.setScene(multiplayerMenu);
    }

    private void hostGame() {
        multiplayerMenu.showHostIp();
        mainWindow.removeScene(multiplayerMenu);
        // some timeout prob
        initializeGame();
        host = new Host(gameModel);
    }

    private void joinGame() {
        String ip = multiplayerMenu.writeHostIp();
        Client client = new Client(ip);

        mainWindow.removeScene(multiplayerMenu);
        gameField = new GameField(systemConfig.getScreenSize());

        mainWindow.setScene(gameField);

        playerController = new PlayerController(systemConfig.getScreenSize());
        mainWindow.setController(playerController);

        playerController.setFocusable(true);
        playerController.requestFocusInWindow();

        Player player = new Player(playerController);
        new Thread(() -> {
            while (true) {
                client.sendPlayerInputInfo(player);
                try {
                    sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                SavedGame savedGame = client.receiveGameData();
                ArrayList<Movable> movables = savedGame.getMovables();
                ArrayList<Obstacle> obstacles = savedGame.getObstacles();
                assignTexture(movables);
                assignTextureObs(obstacles);
                update(movables, obstacles);
            }
        }).start();
    }

    private void assignTexture(ArrayList<Movable> entities) {
        for (Entity entity : entities) {
            entity.setTexture();
        }
    }

    // todo fix this bullshit using polymorphism
    private void assignTextureObs(ArrayList<Obstacle> entities) {
        for (Entity entity : entities) {
            entity.setTexture();
        }
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
