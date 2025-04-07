package task3.network;

import task3.model.commands.player.ControllerCommand;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;

public class PlayerInputInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String id;

    private final LinkedList<ControllerCommand> commands;
    private final Point shootPoint;
    private final Point lookPoint;

    public String getId() {
        return id;
    }

    public Point getShootPoint() {
        return shootPoint;
    }

    public LinkedList<ControllerCommand> getCommands() {
        return commands;
    }

    public Point getLookPoint() {
        return lookPoint;
    }

    public PlayerInputInfo(String id, LinkedList<ControllerCommand> commands, Point shootPoint, Point lookPoint) {
        this.id = id;
        this.commands = commands;
        this.shootPoint = shootPoint;
        this.lookPoint = lookPoint;
    }
}
