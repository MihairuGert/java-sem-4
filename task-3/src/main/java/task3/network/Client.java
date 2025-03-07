package task3.network;

import task3.entity.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket server;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public Client(String ip) throws Exception {
        try {
            server = new Socket(ip, 49001);
            objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(server.getInputStream());
        } catch (IOException e) {
            throw new Exception("Server not found.");
        }
    }

    public void sendPlayerInputInfo(Player player) {
        try {
            objectOutputStream.writeObject(new PlayerInputInfo(player.getInput(), player.getMousePoint(), player.getLookPoint()));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public SavedGame receiveGameData() {
        try {
            return (SavedGame) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
