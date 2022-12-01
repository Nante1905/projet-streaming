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

import converter.Tomkv;
import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;

public class Server {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, IllegalArgumentException, InputFormatException, EncoderException {
        ServerSocket serve = new ServerSocket(4444);
        while(true) {
            Socket client = serve.accept();
            System.out.println(client.getInetAddress().getHostName());
            Thread clientSend = new Thread(new ServerListenerThread(client));
            clientSend.start();
        }
        // String msg = "abcdefghij";
        // File f = new File("C:/Users/minoh/ITU/L2/S3/sys-admin/projet-streaming/assets/Astrid.mp3");
        // FileInputStream inputStream = new FileInputStream(f);
        // byte[] bytes = inputStream.readAllBytes();
        // while (true) {
        //     Socket client = serve.accept();
        //     System.out.println(client.getInetAddress());
        //     DataOutputStream out = new DataOutputStream(client.getOutputStream());
        //     out.writeUTF(f.getName().toLowerCase());
        //     out.write(bytes);
        // }
        // File f = new File("C:/Users/minoh/ITU/L2/S3/sys-admin/projet-streaming/assets/video/test.mp4");
        // File converted = Tomkv.convert(f);

        // FileInputStream in = new FileInputStream(converted);
        // byte[] bytes = in.readAllBytes();
        // System.out.println(bytes.length);

        // while (true) {
        //     Socket client = serve.accept();
        //     System.out.println(client.getInetAddress());
        //     DataOutputStream out = new DataOutputStream(client.getOutputStream());
        //     out.writeUTF(converted.getName().toLowerCase());
        //     out.write(bytes);
        // }

    }
}
