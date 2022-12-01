package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerListenerThread implements Runnable {

    Socket client;
    Thread current;

    public ServerListenerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        DataInputStream in;
        try {
            while (true) {
                in = new DataInputStream(client.getInputStream());
                String reqClient = in.readUTF();
                System.out.println(reqClient);
                if(current != null) {
                    current.interrupt();
                    System.out.println("current flux interrupted");
                    System.out.println(current.isAlive());
                }
                Thread send = new Thread(new ServerSenderThread(client, reqClient));
                this.current = send;
                send.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
