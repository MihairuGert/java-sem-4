package task3.entity;

import task3.controller.Controller;
import task3.server.commands.player.ControllerCommand;

import java.util.LinkedList;

public class Player extends Entity{
    private final Controller controller;

    public Player(Controller controller) {
        this.controller = controller;
    }

    public LinkedList<ControllerCommand> getInput() {
        return controller.getActiveCommands();
    }
}
