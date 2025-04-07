package task3.network;

import task3.engine.commands.player.ControllerCommand;
import task3.engine.Point;
import task3.entity.Player;

public class ClientHandler {
    private final String playerId;
    private Player player;

    private PlayerInputInfo playerInputInfo;

    public ClientHandler(String playerId) {
        this.playerId = playerId;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPlayerInputInfo(PlayerInputInfo playerInputInfo) {
        this.playerInputInfo = playerInputInfo;
        player.setLookPoint(playerInputInfo.getLookPoint());
        player.setActiveCommands(playerInputInfo.getCommands());
        player.setActivePoint(playerInputInfo.getShootPoint());
    }
}
