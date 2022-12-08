package client;

import java.io.DataInputStream;
import java.io.FileOutputStream;

public class Writer implements Runnable {
    DataInputStream in;
    FileOutputStream out;

    

    public Writer(DataInputStream in, FileOutputStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        try {
            int len = 100;
            byte[] read = new byte[len];
            int readable = in.read(read);
            System.out.println("Loading video ...");
            // int i = 0;
            // while(readable != -1) {
            //     out.write(read);
            //     readable = in.read(read);
            //     // Thread.sleep(1000);
            //     System.out.println(i);
            //     i++;
            // }
            while(readable == len) {
                // System.out.println(readable);
                out.write(read);
                readable = in.read(read);
                // Thread.sleep(1000);
            }
            byte[] ambiny = new byte[readable];
            for(int i = 0; i<readable; i++) {
                ambiny[i] = read[i];
            }
            out.write(ambiny);
            System.out.println("video loaded");
            out.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
