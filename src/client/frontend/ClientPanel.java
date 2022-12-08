package client.frontend;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ClientPanel extends JPanel {

    Socket server;

    public ClientPanel(Socket server) throws IOException {
        this.server = server;
        DataInputStream in = new DataInputStream(this.server.getInputStream());
        String data = in.readUTF();
        String[] dataList = data.split(";");
        int songiter = 1;
        int viditer = 1;
        int imgiter = 1;
        RequesterListener listener = new RequesterListener(server);
        for (String file : dataList) {
            String req = "";
            if(file.contains(".mp3")) {
                req = "song:"+songiter;
                songiter++;
            }
            else if(file.contains(".png")) {
                req = "img:"+imgiter;
                imgiter++;
            }
            else if(file.contains(".mp4")) {
                req = "vid:"+viditer;
                viditer++;
            }
            JButton btn = new JButton(file);
            btn.setActionCommand(req);
            btn.addMouseListener(listener);
            this.add(btn);
        }
        this.setVisible(true);
    }
}
