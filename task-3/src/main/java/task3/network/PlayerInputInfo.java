package task3.network;

import task3.server.commands.player.ControllerCommand;
import task3.view.Scene;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;

public class PlayerInputInfo implements Serializable {
    private final LinkedList<ControllerCommand> commands;
    private final Point point;

    public Point getPoint() {
        return point;
    }

    public LinkedList<ControllerCommand> getCommands() {
        return commands;
    }

    public PlayerInputInfo(LinkedList<ControllerCommand> commands, Point point) {
        this.commands = commands;
        this.point = point;
    }
}
