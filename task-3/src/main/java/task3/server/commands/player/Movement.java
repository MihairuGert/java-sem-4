package task3.server.commands.player;

import task3.entity.Movable;

import java.util.LinkedList;

public class Movement {
    static public void execute(Movable player, LinkedList<ControllerCommand> commands) {
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
            if (!player.getClass().getName().equals("task3.entity.Undead")) {
                player.setSize(30, 30);
                player.setVelocity(8);
            }
        }
    }
}
