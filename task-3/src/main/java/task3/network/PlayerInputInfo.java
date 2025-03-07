package task3.network;

import task3.server.commands.player.ControllerCommand;
import task3.view.Scene;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;

public class PlayerInputInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private final LinkedList<ControllerCommand> commands;
    private final Point shootPoint;
    private final Point lookPoint;

    public Point getShootPoint() {
        return shootPoint;
    }

    public LinkedList<ControllerCommand> getCommands() {
        return commands;
    }

    public Point getLookPoint() {
        return lookPoint;
    }

    public PlayerInputInfo(LinkedList<ControllerCommand> commands, Point shootPoint, Point lookPoint) {
        this.commands = commands;
        this.shootPoint = shootPoint;
        this.lookPoint = lookPoint;
    }
}
