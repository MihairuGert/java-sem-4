package task3.entity;

import task3.engine.Point;
import task3.engine.commands.player.ControllerCommand;

import java.util.LinkedList;

public class Player extends Attacking{
    private static final long serialVersionUID = 1L;
    private final String id;

    public void setLookPoint(Point lookPoint) {
        this.lookPoint = lookPoint;
    }

    private Point lookPoint;

    public Player() {
        super();
        id = generateId();
    }

    private String generateId() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            str.append((int) (Math.random() * 100) % 10);
        }
        return str.toString();
    }

    public String getId() {
        return id;
    }

    private void calculateAngle() {
        int playerCenterX = x + xSize / 2;
        int playerCenterY = y + ySize / 2;
        if (lookPoint == null) {
            rotationAngle = 0;
            return;
        }
        double norm = Math.hypot(lookPoint.y() - playerCenterY, lookPoint.x() - playerCenterX);
        if (lookPoint.x() > playerCenterX) {
            rotationAngle = (Math.acos((double)(playerCenterY - lookPoint.y()) / norm))*180/Math.PI;
        } else if (lookPoint.x() < playerCenterX) {
            rotationAngle = (Math.acos((double)(lookPoint.y() - playerCenterY) / norm))*180/Math.PI + 180;
        } else if (lookPoint.y() >= playerCenterY) {
            rotationAngle = 0;
        } else {
            rotationAngle = 180;
        }
    }

    public Point getLookPoint() {
        return lookPoint;
    }

    @Override
    public LinkedList<ControllerCommand> getInput() {
        calculateAngle();
        return super.getInput();
    }
}
