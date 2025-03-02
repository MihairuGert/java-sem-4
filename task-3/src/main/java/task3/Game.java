package task3;

import task3.controller.PlayerController;
import task3.entity.Movable;
import task3.entity.Player;
import task3.server.EventLoop;
import task3.server.EventLoopListener;
import task3.view.MainWindow;
import task3.controller.SystemConfig;
import task3.view.GameField;
import task3.view.Menu;
import task3.view.MenuListener;

import java.awt.*;

public class Game implements MenuListener, EventLoopListener {
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
        EventLoop eventLoop = new EventLoop(gameField, this);
        eventLoop.addPlayer(player);
    }

    @Override
    public void startMultiplayer() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void endGame() {
        mainWindow.removeScene(gameField);
        mainWindow.remove(playerController);
        continueGame();
    }
}
