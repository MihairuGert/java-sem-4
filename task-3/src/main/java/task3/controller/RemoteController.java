package task3.controller;

import task3.network.ClientHandler;
import task3.server.commands.player.ControllerCommand;

import java.awt.*;
import java.util.LinkedList;

public class RemoteController implements Controller {
    ClientHandler clientHandler;

    RemoteController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public LinkedList<ControllerCommand> getActiveCommands() {
        return clientHandler.receiveInputCommands();
    }

    @Override
    public Point getPoint() {
        return clientHandler.receivePoint();
    }

    @Override
    public void setPointNull() {

    }
}
