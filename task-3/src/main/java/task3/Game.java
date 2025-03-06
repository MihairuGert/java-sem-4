package task3;

import task3.controller.PlayerController;
import task3.entity.Movable;
import task3.entity.Obstacle;
import task3.entity.Player;
import task3.server.GameModel;
import task3.server.GameModelListener;
import task3.view.*;
import task3.controller.SystemConfig;
import task3.view.Menu;

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
        mainWindow.removeScene(gameMenu);
        gameField = new GameField(systemConfig.getScreenSize());

        mainWindow.setScene(gameField);

        playerController = new PlayerController(systemConfig.getScreenSize());
        mainWindow.setController(playerController);

        playerController.setFocusable(true);
        playerController.requestFocusInWindow();

        Player player = new Player(playerController);
        GameModel gameModel = new GameModel(this);
        gameModel.addPlayer(player);
    }

    @Override
    public void startMultiplayerMenu() {
        mainWindow.remove(gameMenu);
        mainWindow.setScene(multiplayerMenu);
    }

    private void hostGame() {
        multiplayerMenu.showHostIp();
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(49001)) {
                System.out.println("Я сказала стартуем");
                Socket client = serverSocket.accept();
                System.out.println("Есть контакт");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }

    private void joinGame() {
        String ip = multiplayerMenu.writeHostIp();
        try (Socket socket = new Socket("192.168.0.120", 49001)) {

        } catch (IOException e) {
            System.out.println(e.getMessage());
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
        gameField.setPlayers(movables);
        gameField.setObstacles(obstacles);
        gameField.repaint();
    }
}
