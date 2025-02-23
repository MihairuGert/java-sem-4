package task3;

import task3.controller.Controller;
import task3.server.ControllerCommand;

import java.util.LinkedList;

public class User {
    private final Controller controller;
    User() {
        controller = new Controller();
    }
    public LinkedList<ControllerCommand> getInput() {
        return controller.getActiveCommands();
    }
}
