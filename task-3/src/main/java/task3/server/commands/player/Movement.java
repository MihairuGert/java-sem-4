package task3.server.commands.player;

import task3.entity.Player;

import java.util.LinkedList;

public class Movement {
    static public void execute(Player player, LinkedList<ControllerCommand> commands) {
        for (ControllerCommand command : commands) {
            switch (command) {
                case UP:
                    player.move(0, player.getVelocity());
                    break;
                case DOWN:
                    player.move(0, -player.getVelocity());
                    break;
                case RIGHT:
                    player.move(player.getVelocity(), 0);
                    break;
                case LEFT:
                    player.move(-player.getVelocity(), 0);
                    break;
                default:
                    break;
            }
        }
    }
}
