package task3;

import task3.controller.PlayerController;
import task3.entity.Movable;
import task3.entity.Obstacle;
import task3.entity.Player;
import task3.server.GameModel;
import task3.server.GameModelListener;
import task3.view.MainWindow;
import task3.controller.SystemConfig;
import task3.view.GameField;
import task3.view.Menu;
import task3.view.MenuListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game implements MenuListener, GameModelListener {
    private SystemConfig systemConfig;
    private MainWindow mainWindow;
    private Menu menu;
    private GameField gameField;
    private PlayerController playerController;

    public void runGame() {
        systemConfig = new SystemConfig();
        mainWindow = new MainWindow();

        menu = new Menu(systemConfig.getScreenSize(), this);
        mainWindow.setScene(menu);
    }

    private void continueGame() {
        mainWindow.setScene(menu);
    }

    @Override
    public void startSingleplayer() {
        mainWindow.removeScene(menu);
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
    public void startMultiplayer() {

    }

    @Override
    public void exit() {
        mainWindow.dispose();
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
