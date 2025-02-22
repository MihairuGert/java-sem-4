package task3;

import task3.client.MainWindow;
import task3.client.SystemConfig;

public class Game {
    public void runGame() {
        SystemConfig systemConfig = new SystemConfig();

        Scene scene = new Scene(systemConfig.getScreenSize());

        MainWindow mainWindow = new MainWindow();
        mainWindow.setScene(scene);
    }

}
