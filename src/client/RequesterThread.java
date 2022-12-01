package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class RequesterThread implements Runnable {

    Socket server;
    Thread current;

    public RequesterThread(Socket server) {
        this.server = server;
    }

    @Override
    public void run() {
        DataOutputStream out;
        // DataInputStream in;
        Scanner sc = new Scanner(System.in);
        while(true) {
            try {
                out = new DataOutputStream(server.getOutputStream());
                // in = new DataInputStream(server.getInputStream());
                String req = sc.nextLine();
                out.writeUTF(req);
                // System.out.println("Waiting for server ...");
                // Thread.sleep(5000);
                if(current!=null) {
                    this.current.interrupt();
                    System.out.println("current action stoped : "+this.current.isAlive());
                }
                Thread listener = new Thread(new ListenerThread(this.server));
                this.current = listener;
                listener.start();
            } catch (Exception e) {
                e.printStackTrace();
            }    
        }
    }
    
}
