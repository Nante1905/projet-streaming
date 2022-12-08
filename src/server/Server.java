package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import javax.sound.sampled.UnsupportedAudioFileException;

import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;

public class Server {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, IllegalArgumentException, InputFormatException, EncoderException {
        int PORT = 4444;
        ServerSocket serve = new ServerSocket(PORT);
        System.out.println("Server listening on port " + PORT);
        while(true) {
            Socket client = serve.accept();
            System.out.println(client.getInetAddress().getHostName() + " join the lobby");
            Thread clientSend = new Thread(new ServerListenerThread(client));
            clientSend.start();
        }
    }
}
