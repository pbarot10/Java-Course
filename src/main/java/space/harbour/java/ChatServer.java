package space.harbour.java;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8808);
        while (true) {
            Socket client = server.accept();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(client.getOutputStream()));



        }
    }
}
