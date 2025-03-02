package task3;

import task3.controller.PlayerController;
import task3.entity.Player;
import task3.server.EventLoop;
import task3.view.MainWindow;
import task3.controller.SystemConfig;
import task3.view.GameField;
import task3.view.Menu;
import task3.view.MenuListener;

import javax.swing.*;

public class Game implements MenuListener {
    private SystemConfig systemConfig;
    private MainWindow mainWindow;
    private Menu menu;

    public void runGame() {
        systemConfig = new SystemConfig();
        mainWindow = new MainWindow();

        menu = new Menu(systemConfig.getScreenSize(), this);
        mainWindow.setScene(menu);
    }

    @Override
    public void startSingleplayer() {
        mainWindow.removeScene(menu);
        GameField gameField = new GameField(systemConfig.getScreenSize());

        mainWindow.setScene(gameField);

        PlayerController playerController = new PlayerController(systemConfig.getScreenSize());
        mainWindow.setController(playerController);

        playerController.setFocusable(true);
        playerController.requestFocusInWindow();

        Player player = new Player(playerController);
        EventLoop eventLoop = new EventLoop(gameField);
        eventLoop.addPlayer(player);
    }

    @Override
    public void startMultiplayer() {

    }

    @Override
    public void exit() {

    }
}
