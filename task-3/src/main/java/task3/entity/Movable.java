package task3.entity;

import task3.controller.Controller;
import task3.server.commands.player.ControllerCommand;
import task3.weapon.Melee;
import task3.weapon.Weapon;

import java.awt.*;
import java.util.LinkedList;

public class Movable extends Entity{
    private static final long serialVersionUID = 1L;
    transient protected Controller controller;

    public void setWeapon(Weapon weapon) {
        this.weapon = new Melee();
    }

    protected Weapon weapon;
    protected int velocity = 8;

    public Movable(Controller playerController) {
        this.controller = playerController;
        xSize = 30;
        ySize = 30;
        weapon = new Weapon();
    }
    public LinkedList<ControllerCommand> getInput() {
        return controller.getActiveCommands();
    }
    public Point getMousePoint() {
        return controller.getPoint();
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
        weapon.isHit(new Point(x + xSize / 2, y + ySize / 2), point, entity);
    }
    public Entity whoWasKilled() {
        controller.setPointNull();
        return weapon.whoWasKilled();
    }
}
