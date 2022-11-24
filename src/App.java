import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class App {
    public static void main(String[] args) throws Exception {
        File f = new File("C:/Users/minoh/ITU/L2/S3/sys-admin/byte-server-test/assets/song.mp3");
        FileInputStream in = new FileInputStream(f);
        Player p = new Player(in);
        p.play();
    }
}
