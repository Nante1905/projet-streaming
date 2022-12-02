package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
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
        DataOutputStream out;
        try {
            while (true) {
                out = new DataOutputStream(client.getOutputStream());
                File dir = new File("assets/video");
                File[] files = dir.listFiles();
                String filesList = "";
                for (File file : files) {
                    filesList += file.getName();
                    filesList += ";";
                }
                dir = new File("assets/audio");
                files = dir.listFiles();
                for (File file : files) {
                    filesList += file.getName();
                    filesList += ";";
                }
                dir = new File("assets/img");
                files = dir.listFiles();
                for (File file : files) {
                    filesList += file.getName();
                    filesList += ";";
                }
                out.writeUTF(filesList);

                in = new DataInputStream(client.getInputStream());
                String reqClient = in.readUTF();
                System.out.println(reqClient);
                if(current != null) {
                    current.interrupt();
                    System.out.println("current flux interrupted");
                    System.out.println(current.isAlive());
                }
                Thread send = new Thread(new ServerSenderThread(client, reqClient, out));
                this.current = send;
                send.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
