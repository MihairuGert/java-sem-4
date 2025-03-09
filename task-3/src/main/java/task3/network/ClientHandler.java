package task3.network;

import task3.model.commands.player.ControllerCommand;

import java.awt.*;
import java.util.LinkedList;

public class ClientHandler {
    private String playerId;

    private PlayerInputInfo playerInputInfo;

    public ClientHandler(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerInputInfo(PlayerInputInfo playerInputInfo) {
        this.playerInputInfo = playerInputInfo;
    }

    public LinkedList<ControllerCommand> receiveInputCommands() {
        return playerInputInfo.getCommands();
    }

    public Point receiveShootPoint() {
        return playerInputInfo.getShootPoint();
    }

    public Point receiveLookPoint() {
        return playerInputInfo.getLookPoint();
    }
}
