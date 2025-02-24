package task3.entity;

import task3.controller.Controller;
import task3.controller.PlayerController;
import task3.server.commands.player.ControllerCommand;

import java.util.LinkedList;

public class Player extends Entity{
    protected Controller playerController;
    protected int velocity = 8;
    public Player(Controller playerController) {
        this.playerController = playerController;
        xSize = 30;
        ySize = 30;
    }
    public LinkedList<ControllerCommand> getInput() {
        return playerController.getActiveCommands();
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
