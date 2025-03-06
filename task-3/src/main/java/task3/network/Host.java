package task3.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Host {
    LinkedList<ClientHandler> clientHandlers;
    HostListener hostListener;
    private boolean isGameOver = false;
    ServerSocket serverSocket;

    public Host(HostListener hostListener) {
        this.hostListener = hostListener;
        clientHandlers = new LinkedList<>();
        try {
            serverSocket = new ServerSocket(49001);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        new Thread(this::startListen).start();
    }

    private void startListen() {
        // is game over?
        while (true) {
            try {
                System.out.println("Я сказала стартуем");
                Socket client = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                clientHandlers.add(clientHandler);
                hostListener.newClient(clientHandler);
                System.out.println("Есть контакт");
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
}
