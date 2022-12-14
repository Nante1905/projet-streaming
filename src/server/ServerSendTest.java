package server;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.net.SocketException;

import server.converter.Tomkv;

public class ServerSendTest extends Thread {

    Socket client;
    String reqClient;
    DataOutputStream test;

    public ServerSendTest(Socket client, String reqClient, DataOutputStream test) {
        this.client = client;
        this.reqClient = reqClient;
        this.test = test;
    }

    @Override
    public void run() {
        DataOutputStream out;
        try {
            out = new DataOutputStream(client.getOutputStream());
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
                    
                test.writeUTF(converted.getName().toLowerCase());
                System.out.println(converted.getName());
                // while (true) {
                test.write(bytes);
                test.flush();
                convertedInput.close();
                while(converted.exists()) {
                    converted.delete();
                }
                // }
            }
            else if (type.equals("img")) {
                File imgDir = new File("assets/img");
                File[] allImg = imgDir.listFiles();
                int toSendIndex = Integer.parseInt(num);
                File toSend = allImg[toSendIndex-1];
                FileInputStream in = new FileInputStream(toSend);
                byte[] bytes = in.readAllBytes();
                System.out.println(bytes.length);
                    
                test.writeUTF(toSend.getName().toLowerCase());
                test.writeUTF(String.valueOf(bytes.length));
                System.out.println(toSend.getName());
                test.write(bytes);

                in.close();
            }
            else if(type.equals("song")) {
                File audioDir = new File("assets/audio");
                File[] allAudio = audioDir.listFiles();
                int toSendIndex = Integer.parseInt(num);
                File toSend = allAudio[toSendIndex-1];
                FileInputStream in = new FileInputStream(toSend);
                byte[] bytes = in.readAllBytes();
                System.out.println(bytes.length);
                    
                test.writeUTF(toSend.getName().toLowerCase());
                System.out.println(toSend.getName());
                test.write(bytes);

                in.close();
            }
        } catch (SocketException e) {
            // e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public String getReqClient() {
        return reqClient;
    }

    public void setReqClient(String reqClient) {
        this.reqClient = reqClient;
    }

    public DataOutputStream getTest() {
        return test;
    }

    public void setTest(DataOutputStream test) {
        this.test = test;
    }

    
    
}
