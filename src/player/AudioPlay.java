package player;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioPlay implements Runnable {

    byte[] b;

    public AudioPlay(byte[] b) {
        this.b = b;
    }

    @Override
    public void run() {
        try {
            play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | JavaLayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(this.b));
        Player player = new Player(in);
        player.play();
    }
    
}
