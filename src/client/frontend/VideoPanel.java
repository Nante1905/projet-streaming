package client.frontend;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;

public class VideoPanel extends JPanel {

    Socket server;

    public VideoPanel(Socket server) throws IOException {
        this.server = server;
        DataInputStream in = new DataInputStream(this.server.getInputStream());
        String videoData = in.readUTF();
        String[] videos = videoData.split(";");
        int songiter = 1;
        int viditer = 1;
        int imgiter = 1;
        for (String video : videos) {
            String req = "";
            if(video.contains(".mp3")) {
                req = "song:"+songiter;
                songiter++;
            }
            else if(video.contains(".png")) {
                req = "img:"+imgiter;
                imgiter++;
            }
            else if(video.contains(".mp4")) {
                req = "vid:"+viditer;
                viditer++;
            }
            JButton btn = new JButton(video);
            btn.setActionCommand(req);
            btn.addMouseListener(new RequesterListener(this.server));
            this.add(btn);
        }
        this.setVisible(true);
    }
}
