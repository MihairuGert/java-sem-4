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

    public Client(String ip) {
        try {
            //"192.168.0.120"
            server = new Socket("localhost", 49001);
            objectOutputStream = new ObjectOutputStream(server.getOutputStream());
            objectInputStream = new ObjectInputStream(server.getInputStream());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void sendPlayerInputInfo(Player player) {
        try {
            objectOutputStream.writeObject(new PlayerInputInfo(player.getInput(), player.getMousePoint()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public SavedGame receiveGameData() {
        try {
            return (SavedGame) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
