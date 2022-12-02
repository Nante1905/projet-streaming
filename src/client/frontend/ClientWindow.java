package client.frontend;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;

public class ClientWindow extends JFrame {
    
    Socket server;

    public ClientWindow(Socket server) throws IOException {
        this.server = server;
        VideoPanel vidPan = new VideoPanel(this.server);
        // MusicPanel musicpan = new MusicPanel(this.server);
        // ImgPanel imgpan = new ImgPanel(this.server);
        this.add(vidPan);
        // this.add(musicpan);
        // this.add(imgpan);
        setBounds(new Rectangle(200,200,600,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // DataInputStream in = new DataInputStream(this.server.getInputStream());
        // String listHira = in.readUTF();
        // String[] hiras = listHira.split(";");
        // for (String hira : hiras) {
        //     JButton btn = new JButton();
        // }
    }
}
