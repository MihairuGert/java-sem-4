package task3.engine.commands.player;

import task3.entity.Movable;
import task3.entity.Player;
import task3.entity.Undead;

import java.util.LinkedList;

public class Movement {
    static public void execute(Movable movable, LinkedList<ControllerCommand> commands) {
        if (commands == null) {
            return;
        }
        for (ControllerCommand command : commands) {
            switch (command) {
                case UP:
                    movable.move(movable.getX(), movable.getNegativeYNextPosition());
                    break;
                case DOWN:
                    movable.move(movable.getX(), movable.getPositiveYNextPosition());
                    break;
                case RIGHT:
                    movable.move(movable.getPositiveXNextPosition(), movable.getY());
                    break;
                case LEFT:
                    movable.move(movable.getNegativeXNextPosition(), movable.getY());
                    break;
                case CROUCH:
                    movable.setSize(20,20);
                    movable.setVelocity(4);
                    break;
                default:
                    break;
            }
        }
        if (!commands.contains(ControllerCommand.CROUCH)) {
            if (!(movable instanceof Undead)) {
                movable.setSize(30, 30);
                movable.setVelocity(8);
            }
        }
    }
}
