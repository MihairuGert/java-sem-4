package task3.controller;

import task3.server.commands.player.ControllerCommand;

import java.util.LinkedList;

public interface Controller {
    LinkedList<ControllerCommand> getActiveCommands();
}
