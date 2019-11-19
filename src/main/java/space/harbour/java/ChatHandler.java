package space.harbour.java;

import java.io.*;
import java.net.Socket;

public class ChatHandler extends Thread {
    final Socket client;
    final BufferedReader in;
    final PrintWriter out;
    private String name;

    public ChatHandler(ChatServer servers, Socket client) throws IOException {
        this.client = client;
        in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(
                new OutputStreamWriter(client.getOutputStream()));
    }

    public void greeting() throws IOException {
        out.println("Hello! This is Echo server");
        out.println("Type your name or type BYE to exit");
        out.flush();
        setName(in.readLine());
    }

    @Override
    public void run() {
        try (client; in; out) {
            out.println("Hello! This is Echo server");
            out.println("Type BYE to exit");
            out.flush();

            while (true) {
                String s = in.readLine();
                if (s == null) break;

                out.println("Echo: " + s);
                out.flush();

                if (s.trim().equals("BYE")) break;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
