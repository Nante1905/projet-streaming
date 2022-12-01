package client;

import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import player.AudioPlay;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

/**
 * Client
 */
public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException,
            UnsupportedAudioFileException, LineUnavailableException, JavaLayerException {
        Socket client = new Socket("127.0.0.1", 4444);
        DataInputStream in = new DataInputStream(client.getInputStream());
        // FileOutputStream outputStream = new FileOutputStream(new
        // File("C:/Users/minoh/ITU/L2/S3/sys-admin/byte-server-test/assets/response.mp3"));

        int len = 100000;
        // int receive = 0;
        int i = 0;
        byte[] b = new byte[len];
        byte msg;
        String filename = in.readUTF();
        System.out.println(filename);

        /* Music player */
        if (filename.contains(".mp3")) {
            while (true) {
                in.readFully(b, 0, len);
                play(b);
                System.out.println("playing ...");
                // if(i < len) {
                // b[i] = msg;
                // i++;
                // }
                // else if(i >= len) {
                // System.out.println("playing ...");
                // // Thread play = new Thread(new AudioPlay(b));
                // // play.start();
                // play(b);
                // i=0;
                // }
            }
        }
        /* ------------------------------ */
        else if (filename.contains(".jpg") || filename.contains(".png") || filename.contains(".jpeg")) {
        }
        else if (filename.contains(".mkv")) {
            File temp = File.createTempFile("temp", "mkv");
            FileOutputStream out = new FileOutputStream(temp);
            Thread write = new Thread(new Writer(in, out));
            write.start();
            EmbeddedMediaPlayerComponent component = new EmbeddedMediaPlayerComponent();
            JFrame f = new JFrame();
            f.setContentPane(component);
            f.setBounds(new Rectangle(200, 200, 800, 600));
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setVisible(true);
            Thread.sleep(2000);
            component.mediaPlayer().media().play(temp.toPath().toString());
        }

    }

    public static void play(byte[] data)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(data));
        Player player = new Player(in);
        player.play();
    }
}