package ch.marth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final String webRoot;

    public ClientHandler(Socket clientSocket, String webRoot) {
        this.clientSocket = clientSocket;
        this.webRoot = webRoot;
    }

    @Override
    public void run() {
        try {
            // read the request
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            HttpRequest request = new HttpRequest(reader);

            // handle the request
            HttpResponse response = new HttpResponse(webRoot, request);

            // write the response
            OutputStream out = clientSocket.getOutputStream();
            response.write(out);

            // close the client socket
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
