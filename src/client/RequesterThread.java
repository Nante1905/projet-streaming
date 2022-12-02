package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class RequesterThread implements Runnable {

    Socket server;
    Thread current;
    String req;

    public RequesterThread(Socket server, String req) {
        this.server = server;
        this.req = req;
    }

    @Override
    public void run() {
        DataOutputStream out;
        // DataInputStream in;
        // Scanner sc = new Scanner(System.in);
        // while(true) {
            try {
                out = new DataOutputStream(server.getOutputStream());
                // in = new DataInputStream(server.getInputStream());
                // String req = sc.nextLine();
                out.writeUTF(this.req);
                System.out.println("Requested data");
                // System.out.println("Waiting for server ...");
                // Thread.sleep(5000);
                if(current!=null) {
                    this.current.interrupt();
                    System.out.println("current action stoped : "+this.current.isAlive());
                }
                Thread listener = new Thread(new ListenerThread(this.server));
                this.current = listener;
                listener.start();
                System.out.println("Strating listening ..");
            } catch (Exception e) {
                e.printStackTrace();
            }    
        // }
    }
    
}
