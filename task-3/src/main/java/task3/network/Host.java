package task3.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Host implements ClientHandlerListener {
    LinkedList<ClientHandler> clientHandlers;
    private boolean isGameOver = false;
    ServerSocket serverSocket;

    public Host() {
        clientHandlers = new LinkedList<>();
        try {
            serverSocket = new ServerSocket(49001);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        new Thread(this::startListen).start();
    }

    private void startListen() {
        while (!isGameOver) {
            try (ServerSocket serverSocket = new ServerSocket(49001)) {
                Socket client = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(this, client);
                clientHandlers.add(clientHandler);
                System.out.println("Есть контакт");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void sendUpdate(SavedGame savedGame) {
        for (ClientHandler clientHandler : clientHandlers) {

        }
    }
}
