package client;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import player.AudioPlay;

/**
 * Client
 */
public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException, JavaLayerException {
        Socket client = new Socket("127.0.0.1", 4444);
        DataInputStream in = new DataInputStream(client.getInputStream());
        FileOutputStream outputStream = new FileOutputStream(new File("C:/Users/minoh/ITU/L2/S3/sys-admin/byte-server-test/assets/response.mp3"));
        int len = 3000000;
        // int receive = 0;
        int i = 0;
        byte[] b = new byte[len];
        byte msg;
        while(true) {
            msg = in.readByte();
            if(i < len) {
                b[i] = msg;
                i++;
            }
            else if(i >= len) {
                System.out.println("playing ...");
                // Thread play = new Thread(new AudioPlay(b));
                // play.start();
                play(b);
                i=0;
            }
            // msg = in.readByte();
            // b[0] = msg;
            // play(b);
            // System.out.println("Playing ...");
            // b[i] = msg;
            // if(i < len) {
            //     continue;
            // } else {
            //     System.out.println("Playing ...");
            //     Client.play(b);
            //     i = -1;
            //     b = new byte[len];
            // }
            // i++;
            
            // outputStream.write(b);
            // System.out.println(b);
            // Thread.sleep(1000);
        }
    }

    public static void play(byte[] data) throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(data));
        Player player = new Player(in);
        player.play();
    }
    // public static void playo(byte[] data) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    //     AudioInputStream ais = AudioSystem.getAudioInputStream(new ByteArrayInputStream(data));
    //     DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
    //     Clip clip = (Clip) AudioSystem.getLine(info);
    //     clip.open(ais);
    //     clip.start();
    // }
}