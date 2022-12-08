package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import client.frontend.ClientWindow;
import javazoom.jl.decoder.JavaLayerException;

/**
 * Client
 */
public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException,
            UnsupportedAudioFileException, LineUnavailableException, JavaLayerException {
        Socket client = new Socket("127.0.0.1", 4444);
        ClientWindow win = new ClientWindow(client);
    }
}