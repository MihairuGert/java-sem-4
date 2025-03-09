package task3.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;

public class Host {
    private final HashMap<String, ClientHandler> clientHandlers;
    private final HostListener hostListener;
    private DatagramSocket serverSocket;
    private volatile boolean isGameOver;

    private final int packetSize = 4096;

    public Host(HostListener hostListener) {
        this.hostListener = hostListener;
        isGameOver = false;
        clientHandlers = new HashMap<>();
        try {
            serverSocket = new DatagramSocket(49001);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        new Thread(this::start).start();
    }

    private byte[] serializedSavedGame;

    public void start() {
        while (!isGameOver) {
            try {
                byte[] buffer = new byte[packetSize];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet);

                PlayerInputInfo inputInfo = (PlayerInputInfo) Serializer.deserialize(packet.getData());

                String playerId = inputInfo.getId();
                ClientHandler clientHandler = clientHandlers.get(playerId);
                if (clientHandler == null) {
                    clientHandler = new ClientHandler(playerId);
                    hostListener.newClient(clientHandler);
                    clientHandlers.put(playerId, clientHandler);
                    continue;
                }
                clientHandler.setPlayerInputInfo(inputInfo);

                if (serializedSavedGame == null) {
                    continue;
                }
                byte[] response = serializedSavedGame;
                DatagramPacket responsePacket = new DatagramPacket(response, response.length, packet.getAddress(), packet.getPort());
                serverSocket.send(responsePacket);
            } catch (IOException | ClassNotFoundException e) {
                closeConnection();
            }
        }
    }

    public void sendUpdate(SavedGame savedGame) {
        try {
            serializedSavedGame = Serializer.serialize(savedGame);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            serializedSavedGame = null;
        }
    }

    public void closeConnection() {
        isGameOver = true;
        serverSocket.close();
    }
}
