package task3;

import task3.controller.PlayerController;
import task3.entity.Player;
import task3.server.EventLoop;
import task3.view.MainWindow;
import task3.controller.SystemConfig;
import task3.view.GameField;

public class Game {
    public void runGame() {
        SystemConfig systemConfig = new SystemConfig();
        GameField gameField = new GameField(systemConfig.getScreenSize());
        MainWindow mainWindow = new MainWindow();
        mainWindow.setScene(gameField);
        PlayerController playerController = new PlayerController(systemConfig.getScreenSize());
        mainWindow.setController(playerController);
        Player player = new Player(playerController);
        EventLoop eventLoop = new EventLoop(gameField);
        eventLoop.addPlayer(player);
    }

}
