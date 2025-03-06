package task3.network;

import task3.server.commands.player.ControllerCommand;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class ClientHandler {
    private ClientHandlerListener clientHandlerListener;
    private Socket client;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ClientHandler(ClientHandlerListener clientHandlerListener, Socket client) {
        this.clientHandlerListener = clientHandlerListener;
        this.client = client;
        try {
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectInputStream = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public void sendSavedGame(SavedGame savedGame) {
        try {
            objectOutputStream.writeObject(savedGame);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public LinkedList<ControllerCommand> receiveInputCommands() {
        try {
            return ((PlayerInputInfo) objectInputStream.readObject()).getCommands();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    public Point receivePoint() {
        try {
            return ((PlayerInputInfo) objectInputStream.readObject()).getPoint();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
