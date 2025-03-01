package task3;

import task3.controller.PlayerController;
import task3.entity.Player;
import task3.server.EventLoop;
import task3.view.MainWindow;
import task3.controller.SystemConfig;
import task3.view.GameField;
import task3.view.Menu;

public class Game {
    public void runGame() {
        SystemConfig systemConfig = new SystemConfig();

        MainWindow mainWindow = new MainWindow();
        Menu menu = new Menu(systemConfig.getScreenSize());
        mainWindow.setScene(menu);
//        GameField gameField = new GameField(systemConfig.getScreenSize());
//        mainWindow.setScene(gameField);
//        PlayerController playerController = new PlayerController(systemConfig.getScreenSize());
//        mainWindow.setController(playerController);
//        Player player = new Player(playerController);
//        EventLoop eventLoop = new EventLoop(gameField);
//        eventLoop.addPlayer(player);
    }
}
