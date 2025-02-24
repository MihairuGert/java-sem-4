package task3.controller;

import task3.server.commands.player.ControllerCommand;

import java.util.LinkedList;

public class AIController implements Controller {

    @Override
    public LinkedList<ControllerCommand> getActiveCommands() {
        LinkedList<ControllerCommand> action = new LinkedList<>();
        for (ControllerCommand cm : ControllerCommand.values()) {
            if (Math.random() >= 0.5 && cm != ControllerCommand.CROUCH) {
                action.add(cm);
            }
        }
        return action;
    }
}
