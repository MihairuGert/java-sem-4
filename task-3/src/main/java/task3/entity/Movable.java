package task3.entity;

import task3.engine.commands.player.ControllerCommand;

import java.util.LinkedList;

public class Movable extends Entity{
    private static final long serialVersionUID = 1L;

    public void setActiveCommands(LinkedList<ControllerCommand> activeCommands) {
        this.activeCommands = activeCommands;
    }

    private LinkedList<ControllerCommand> activeCommands;

    protected int velocity = 8;

    public Movable() {
        xSize = 30;
        ySize = 30;
    }
    public LinkedList<ControllerCommand> getInput() {
        return activeCommands;
    }

    public int getPositiveXNextPosition() {
        return x + velocity;
    }
    public int getNegativeXNextPosition() {
        return x - velocity;
    }
    public int getPositiveYNextPosition() {
        return y + velocity;
    }
    public int getNegativeYNextPosition() {
        return y - velocity;
    }
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
