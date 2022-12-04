package ch.marth;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BotServer {
    private static final int PORT = 8080;
    private static final String WEB_ROOT = "web-root";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Listening for connections on port " + PORT + "...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Received a connection from " + clientSocket.getInetAddress());

            // start a new thread to handle the request
            new Thread(new ClientHandler(clientSocket, WEB_ROOT)).start();
        }
    }
}
