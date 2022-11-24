package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Server {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
        ServerSocket serve = new ServerSocket(4444);
        String msg = "abcdefghij";
        File f = new File("C:/Users/minoh/ITU/L2/S3/sys-admin/byte-server-test/assets/song.mp3");
        FileInputStream inputStream = new FileInputStream(f);
        // AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);
        // DataInputStream dis = new DataInputStream(inputStream);
        // AudioFormat format = inputStream.getFormat();
        // byte[] bytes = new byte[(int)(inputStream.getFrameLength()*format.getFrameSize())];
        // dis.readFully(bytes);
        // dis.close();
        byte[] bytes = inputStream.readAllBytes();
        // System.out.println(bytes.length);
        while (true) {
            Socket client = serve.accept();
            System.out.println(client.getInetAddress());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            // out.writeUTF(msg);
            out.write(bytes);
        }
    }
}
