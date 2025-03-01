package task3.entity;

import task3.controller.Controller;
import task3.controller.PlayerController;
import task3.server.commands.player.ControllerCommand;
import task3.sound.SoundPlayer;
import task3.weapon.Melee;
import task3.weapon.Weapon;

import java.awt.*;
import java.util.LinkedList;

public class Player extends Entity{
    protected Controller playerController;

    public void setWeapon(Weapon weapon) {
        this.weapon = new Melee();
    }

    protected Weapon weapon;
    protected int velocity = 8;

    public Player(Controller playerController) {
        this.playerController = playerController;
        xSize = 30;
        ySize = 30;
        weapon = new Weapon();
    }
    private void calculateAngle() {
        int playerCenterX = x + xSize / 2;
        int playerCenterY = y + ySize / 2;
        Point lookPoint = ((PlayerController)playerController).getLookPoint();
        if (lookPoint == null) {
            rotationAngle = 0;
            return;
        }
        double norm = Math.hypot(lookPoint.y - playerCenterY, lookPoint.x - playerCenterX);
        if (lookPoint.x > playerCenterX) {
            rotationAngle = (Math.acos((double)(playerCenterY - lookPoint.y) / norm))*180/Math.PI;
        } else if (lookPoint.x < playerCenterX) {
            rotationAngle = (Math.acos((double)(lookPoint.y - playerCenterY) / norm))*180/Math.PI + 180;
        } else if (lookPoint.y >= playerCenterY) {
            rotationAngle = 0;
        } else {
            rotationAngle = 180;
        }
        //System.out.println(rotationAngle);
    }
    public LinkedList<ControllerCommand> getInput() {
        LinkedList<ControllerCommand> linkedList = playerController.getActiveCommands();
        calculateAngle();
        return linkedList;
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
