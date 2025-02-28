package task3;

import task3.controller.PlayerController;
import task3.entity.Player;
import task3.server.EventLoop;
import task3.view.MainWindow;
import task3.controller.SystemConfig;
import task3.view.Scene;
import task3.weapon.Melee;

public class Game {
    public void runGame() {
        SystemConfig systemConfig = new SystemConfig();
        Scene scene = new Scene(systemConfig.getScreenSize());
        MainWindow mainWindow = new MainWindow();
        mainWindow.setScene(scene);
        PlayerController playerController = new PlayerController(systemConfig.getScreenSize());
        mainWindow.setController(playerController);
        Player player = new Player(playerController);
        EventLoop eventLoop = new EventLoop(scene);
        eventLoop.addPlayer(player);
    }

}
