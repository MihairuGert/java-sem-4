package task3.controller;

import task3.network.ClientHandler;
import task3.model.commands.player.ControllerCommand;

import java.awt.*;
import java.util.LinkedList;

public class RemoteController implements Controller {
    private final ClientHandler clientHandler;
    private Point prevPoint;

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
        Point newPoint = clientHandler.receiveShootPoint();
        if (prevPoint != null) {
            if (prevPoint.equals(newPoint)) {
                return null;
            }
        }
        prevPoint = newPoint;
        return prevPoint;
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
