package task3.controller;

import task3.server.commands.player.ControllerCommand;

import java.awt.*;
import java.util.LinkedList;

public class AIController implements Controller {
    @Override
    public LinkedList<ControllerCommand> getActiveCommands() {
        LinkedList<ControllerCommand> action = new LinkedList<>();
        return action;
    }
    @Override
    public Point getPoint() {
        return null;
    }

    @Override
    public void setPointNull() {}

    @Override
    public Point getLookPoint() {
        return null;
    }

}
