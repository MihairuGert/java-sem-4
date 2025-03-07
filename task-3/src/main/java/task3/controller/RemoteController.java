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
        try {
            return clientHandler.receiveInputCommands();
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public Point getPoint() {
        try {
            return clientHandler.receiveShootPoint();
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public void setPointNull() {

    }

    @Override
    public Point getLookPoint() {
        try {
            return clientHandler.receiveLookPoint();
        } catch (RuntimeException e) {
            return null;
        }
    }
}
