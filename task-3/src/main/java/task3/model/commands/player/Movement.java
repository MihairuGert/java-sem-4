package task3.model.commands.player;

import task3.entity.Movable;
import task3.entity.Undead;

import java.util.LinkedList;

public class Movement {
    static public void execute(Movable player, LinkedList<ControllerCommand> commands) {
        if (commands == null) {
            return;
        }
        for (ControllerCommand command : commands) {
            switch (command) {
                case UP:
                    player.move(player.getX(), player.getNegativeYNextPosition());
                    break;
                case DOWN:
                    player.move(player.getX(), player.getPositiveYNextPosition());
                    break;
                case RIGHT:
                    player.move(player.getPositiveXNextPosition(), player.getY());
                    break;
                case LEFT:
                    player.move(player.getNegativeXNextPosition(), player.getY());
                    break;
                case CROUCH:
                    player.setSize(20,20);
                    player.setVelocity(4);
                    break;
                default:
                    break;
            }
        }
        if (!commands.contains(ControllerCommand.CROUCH)) {
            if (!(player instanceof Undead)) {
                player.setSize(30, 30);
                player.setVelocity(8);
            }
        }
    }
}
