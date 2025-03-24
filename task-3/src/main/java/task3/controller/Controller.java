package task3.controller;

import task3.engine.commands.player.ControllerCommand;
import task3.entity.Player;

import task3.engine.Point;
import java.util.LinkedList;

public interface Controller {
    void setActiveCommands();
    Point getPoint();
    void setPointNull();
    Point getLookPoint();
}
