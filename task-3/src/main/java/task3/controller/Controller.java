package task3.controller;

import task3.model.commands.player.ControllerCommand;

import java.awt.*;
import java.util.LinkedList;

public interface Controller {
    LinkedList<ControllerCommand> getActiveCommands();
    Point getPoint();
    void setPointNull();
    Point getLookPoint();
}
