package task3.network;

import task3.entity.Player;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    private final DatagramSocket server;
    private final InetAddress ip;
    private final int port = 49001;
    private final int packetSize = 4096;
    private final int timeout = 1000;

    public Client(String ip) throws Exception {
        this.ip = InetAddress.getByName(ip);
        server = new DatagramSocket();
        server.setSoTimeout(timeout);
    }

    public void sendPlayerData(Player player) {
        PlayerInputInfo playerInputInfo = new PlayerInputInfo(player.getId(),
                player.getInput(), player.getMousePoint(), player.getLookPoint());
        try {
            byte[] data = Serializer.serialize(playerInputInfo);
            DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
            server.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SavedGame receiveSavedData() {
        byte[] data = new byte[packetSize];
        DatagramPacket packet = new DatagramPacket(data, packetSize);
        try {
            server.receive(packet);
            return (SavedGame) Serializer.deserialize(packet.getData());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        server.close();
    }
}