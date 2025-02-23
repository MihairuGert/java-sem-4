package task3;

import task3.controller.Controller;
import task3.entity.Player;
import task3.server.GameLifeCycle;
import task3.view.MainWindow;
import task3.controller.SystemConfig;
import task3.view.Scene;

public class Game {
    public void runGame() {
        SystemConfig systemConfig = new SystemConfig();
        Scene scene = new Scene(systemConfig.getScreenSize());
        MainWindow mainWindow = new MainWindow();
        mainWindow.setScene(scene);
        Controller controller = new Controller();
        Player player = new Player(controller);
        GameLifeCycle gameLifeCycle = new GameLifeCycle();
        gameLifeCycle.addPlayer(player);
    }

}
