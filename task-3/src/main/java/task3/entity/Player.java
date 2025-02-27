package task3.entity;

import task3.controller.Controller;
import task3.controller.PlayerController;
import task3.server.commands.player.ControllerCommand;
import task3.weapon.Weapon;

import java.awt.*;
import java.util.LinkedList;

public class Player extends Entity{
    protected Controller playerController;
    protected Weapon weapon;
    protected int velocity = 8;

    public Player(Controller playerController) {
        this.playerController = playerController;
        xSize = 30;
        ySize = 30;
        weapon = new Weapon();
    }
    public LinkedList<ControllerCommand> getInput() {
        return playerController.getActiveCommands();
    }
    public Point getMousePoint() {
        return playerController.getPoint();
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
    public void tryAttack(Point point, Entity entity) {
        if (point == null) {
            return;
        }
        weapon.isHit(new Point(x, y), point, entity);
    }
    public Entity whoWasKilled() {
        playerController.setPointNull();
        return weapon.whoWasKilled();
    }
}
