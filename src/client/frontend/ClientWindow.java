package client.frontend;

import java.io.IOException;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import java.awt.*;

public class ClientWindow extends JFrame {
    
    Socket server;

    public ClientWindow(Socket server) throws IOException {
        this.server = server;
        ClientPanel clientpanel = new ClientPanel(this.server);
        setBounds(new Rectangle(200,200,600,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(clientpanel);
        setVisible(true);
    }
}
