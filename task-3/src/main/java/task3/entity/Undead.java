package task3.entity;

import task3.controller.Controller;
import task3.server.commands.player.ControllerCommand;
import task3.weapon.ZombieClaw;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.LinkedList;

public class Undead extends Movable {
    Entity entityToChase;

    public Undead(Controller AIController) {
        super(AIController);
        velocity = 1+(int)(Math.random()*100)%2;
        weapon = new ZombieClaw();
        URL iconURL = getClass().getResource("/zombie.png");
        if (iconURL != null)
            texture = new ImageIcon(iconURL).getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT);
    }

    public void setTexture() {
        URL iconURL = getClass().getResource("/zombie.png");
        if (iconURL != null)
            texture = new ImageIcon(iconURL).getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT);
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
