package task3.controller;

import task3.network.ClientHandler;
import task3.model.commands.player.ControllerCommand;

import java.awt.*;
import java.util.LinkedList;

public class RemoteController implements Controller {
    ClientHandler clientHandler;

    public RemoteController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public LinkedList<ControllerCommand> getActiveCommands() {
        return clientHandler.receiveInputCommands();
    }

    @Override
    public Point getPoint() {
        return clientHandler.receiveShootPoint();
    }

    @Override
    public void setPointNull() {
    }

    @Override
    public Point getLookPoint() {
        return clientHandler.receiveLookPoint();
    }
}
