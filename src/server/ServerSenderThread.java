package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import converter.Tomkv;
import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;

public class ServerSenderThread implements Runnable {


    Socket client;
    String reqClient;

    public ServerSenderThread(Socket client, String reqClient) {
        this.client = client;
        this.reqClient = reqClient;
    }

    @Override
    public void run() {
        DataOutputStream out;
        try {
            // in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            // String reqClient = in.readUTF();
            System.out.println(this.reqClient);
            String[] reqClientDetail = this.reqClient.split(":");
            String type = reqClientDetail[0].toLowerCase();
            String num = reqClientDetail[1].toLowerCase();
            if(type.equals("vid")) {
                File vidDir = new File("assets/video");
                File[] allVid = vidDir.listFiles();
                int toSendIndex = Integer.parseInt(num);
                File toSend = allVid[toSendIndex-1];

                File converted = Tomkv.convert(toSend);
                FileInputStream convertedInput = new FileInputStream(converted);
                byte[] bytes = convertedInput.readAllBytes();
                System.out.println(bytes.length);
                    
                out.writeUTF(converted.getName().toLowerCase());
                System.out.println(converted.getName());
                // while (true) {
                out.write(bytes);
                // }
            }
            else if(type.equals("song")) {
                File audioDir = new File("assets/audio");
                File[] allAudio = audioDir.listFiles();
                int toSendIndex = Integer.parseInt(num);
                File toSend = allAudio[toSendIndex-1];
                FileInputStream in = new FileInputStream(toSend);
                byte[] bytes = in.readAllBytes();
                System.out.println(bytes.length);
                    
                out.writeUTF(toSend.getName().toLowerCase());
                System.out.println(toSend.getName());
                out.write(bytes);

                in.close();
            }
            else if (type.equals("img")) {
                File imgDir = new File("assets/img");
                File[] allImg = imgDir.listFiles();
                int toSendIndex = Integer.parseInt(num);
                File toSend = allImg[toSendIndex-1];
                FileInputStream in = new FileInputStream(toSend);
                byte[] bytes = in.readAllBytes();
                System.out.println(bytes.length);
                    
                out.writeUTF(toSend.getName().toLowerCase());
                out.writeUTF(String.valueOf(bytes.length));
                System.out.println(toSend.getName());
                out.write(bytes);

                in.close();
            }
        } catch (SocketException e) {
            // e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    
}
