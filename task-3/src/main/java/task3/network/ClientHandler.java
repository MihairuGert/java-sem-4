package task3.network;

import task3.model.commands.player.ControllerCommand;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class ClientHandler {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket client;

    public ClientHandler(Socket client) {
        try {
            this.client = client;
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void sendSavedGame(SavedGame savedGame) {
        if (client.isClosed()) {
            return;
        }
        try {
            objectOutputStream.writeObject(savedGame);
            objectOutputStream.reset();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public LinkedList<ControllerCommand> receiveInputCommands() {
        if (client.isClosed()) {
            return null;
        }
        try {
            return ((PlayerInputInfo) objectInputStream.readObject()).getCommands();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Point receiveShootPoint() {
        if (client.isClosed()) {
            return null;
        }
        try {
            return ((PlayerInputInfo) objectInputStream.readObject()).getShootPoint();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Point receiveLookPoint() {
        if (client.isClosed()) {
            return null;
        }
        try {
            return ((PlayerInputInfo) objectInputStream.readObject()).getLookPoint();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void closeConnection() {
        try {
            client.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
