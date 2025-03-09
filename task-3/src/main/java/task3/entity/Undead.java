package task3.entity;

import task3.controller.Controller;
import task3.model.commands.player.ControllerCommand;
import task3.weapon.ZombieClaw;

import java.awt.*;
import java.util.LinkedList;

public class Undead extends Movable {
    private Entity entityToChase;
    private int difficultyClass = 10;

    public Undead() {
        super();
    }

    public Undead(Controller AIController) {
        super(AIController);
        velocity = 1+(int)(Math.random()*100)%2;
        weapon = new ZombieClaw();

        trace = new LinkedList<>();
    }

    private LinkedList<Point> trace;

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

    public int getDifficultyClass() {
        return difficultyClass;
    }

    public void setDifficultyClass(int difficultyClass) {
        this.difficultyClass = difficultyClass;
    }
}
