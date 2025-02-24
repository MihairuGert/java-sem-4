package task3.entity;

import task3.controller.Controller;
import task3.server.commands.player.ControllerCommand;

import java.util.LinkedList;

public class Player extends Entity{
    private final Controller controller;
    private final int velocity = 10;
    public Player(Controller controller) {
        this.controller = controller;
        xSize = 30;
        ySize = 30;
    }
    public LinkedList<ControllerCommand> getInput() {
        return controller.getActiveCommands();
    }
    public int getPositiveXNextPosition() {
        return x + velocity;
    }
    public int getNegativeXNextPosition() {
        return x - velocity;
    }
    public int getPositiveYNextPosition() {
        return y + velocity;
    }
    public int getNegativeYNextPosition() {
        return y - velocity;
    }

}
