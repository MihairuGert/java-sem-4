package task3.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Host {
    private CopyOnWriteArrayList<ClientHandler> clientHandlers;
    private HostListener hostListener;
    private ServerSocket serverSocket;
    private volatile boolean isGameOver;

    public Host(HostListener hostListener) {
        this.hostListener = hostListener;
        isGameOver = false;
        clientHandlers = new CopyOnWriteArrayList<>();
        try {
            serverSocket = new ServerSocket(49001);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        new Thread(this::startListen).start();
    }

    private void startListen() {
        while (!isGameOver) {
            try {
                Socket client = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                clientHandlers.add(clientHandler);
                hostListener.newClient(clientHandler);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void sendUpdate(SavedGame savedGame) {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.sendSavedGame(savedGame);
        }
    }

    public void closeConnection() {
        isGameOver = true;
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.closeConnection();
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            if (!isGameOver) {
                System.err.println(e.getMessage());
            }
        }
    }
}
