package task3.entity;

import task3.controller.Controller;
import task3.server.commands.player.ControllerCommand;
import task3.weapon.Weapon;
import task3.weapon.ZombieClaw;

import java.awt.*;
import java.util.LinkedList;

public class Undead extends Player {
    private int visionRangeX = 100;
    private int visionRangeY = 100;
    Entity entityToChase;

    public Undead(Controller AIController) {
        super(AIController);
        velocity = 1+(int)(Math.random()*100)%2;
        weapon = new ZombieClaw();
    }

    public int getVisionRangeX() {
        return visionRangeX;
    }

    public int getVisionRangeY() {
        return visionRangeY;
    }

    public LinkedList<ControllerCommand> getTrace(Entity entity) {
        LinkedList<ControllerCommand> trace = new LinkedList<>();
        if (entity.getX() > x) {
            trace.add(ControllerCommand.RIGHT);
        }
        else if (entity.getX() < x) {
            trace.add(ControllerCommand.LEFT);
        }
        if (entity.getY() < y) {
            trace.add(ControllerCommand.UP);
        }
        else if (entity.getY() > y) {
            trace.add(ControllerCommand.DOWN);
        }
        return trace;
    }

    public void setEntityToChase(Entity entityToChase) {
        this.entityToChase = entityToChase;
    }

    @Override
    public LinkedList<ControllerCommand> getInput() {
        if (entityToChase == null) {
            return new LinkedList<>();
        }
        return getTrace(entityToChase);
    }

    @Override
    public Point getMousePoint() {
        return new Point(x, y);
    }
}
