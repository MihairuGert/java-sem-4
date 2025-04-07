package task3.entity;

import task3.engine.Point;
import task3.engine.commands.player.ControllerCommand;
import task3.weapon.ZombieClaw;

import java.util.LinkedList;

public class Undead extends Attacking {
    private Entity entityToChase;
    private int difficultyClass = 10;

    public Undead() {
        super();
        velocity = 1+(int)(Math.random()*100)%2;
        weapon = new ZombieClaw();
    }

    public void getTrace() {
        if (entityToChase == null) {
            return;
        }
        LinkedList<ControllerCommand> trace = new LinkedList<>();
        if (entityToChase.getX() > x) {
            trace.add(ControllerCommand.RIGHT);
        }
        else if (entityToChase.getX() < x) {
            trace.add(ControllerCommand.LEFT);
        }
        if (entityToChase.getY() < y) {
            trace.add(ControllerCommand.UP);
        }
        else if (entityToChase.getY() > y) {
            trace.add(ControllerCommand.DOWN);
        }
        setActiveCommands(trace);
    }

    public void setEntityToChase(Entity entityToChase) {
        this.entityToChase = entityToChase;
    }

    @Override
    public LinkedList<ControllerCommand> getInput() {
        getTrace();
        if (entityToChase == null) {
            return new LinkedList<>();
        }

        return super.getInput();
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
