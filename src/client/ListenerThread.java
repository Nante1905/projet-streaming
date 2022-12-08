package client;

import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import client.listener.VideoListener;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class ListenerThread implements Runnable {

    Socket server;
    Boolean playaudio;

    public ListenerThread(Socket server) {
        this.server = server;
    }

    @Override
    public void run() {
        // DataOutputStream out;
        DataInputStream in;

        try {
            in = new DataInputStream(server.getInputStream());
            String filename = in.readUTF();
            // int testi = 0;
            // while(filename.equals("a")) {
            //     try {
            //         filename = in.readUTF();
            //     } catch (Exception e) {
            //         in.readByte();
            //     }
            //     System.out.println(testi);
            //     testi++;
            // }
            while(filename.contains(";")) {
                // System.out.println("point virgule");
                filename = in.readUTF();
            }
            // while (true) {
            System.out.println("Checking files "+filename+" ...");
                /* Music player */
                if (filename.contains(".mp3")) {
                    playaudio = true;
                    JLabel songLabel = new JLabel("Playing "+ filename);
                    JFrame f = new JFrame();
                    f.setBounds(new Rectangle(200, 200, 800, 200));
                    f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    f.addWindowListener(new WindowListener() {

                        @Override
                        public void windowOpened(WindowEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void windowClosing(WindowEvent e) {
                            // TODO Auto-generated method stub
                            playaudio = false;
                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void windowIconified(WindowEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void windowActivated(WindowEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {
                            // TODO Auto-generated method stub
                            
                        }

                    });
                    f.add(songLabel);
                    f.setVisible(true);
                    int len = 100000;
                    byte[] b = new byte[len];
                    int readable = len;
                    System.out.println("playing ...");
                    while (playaudio && readable == len) {
                        readable = in.read(b);
                        play(b);
                    }

                    byte[] reste = new byte[readable];
                    for(int i = 0; i<readable; i++) {
                        reste[i] = b[i];
                    }
                    play(b);
                }

                /* IMG */
                else if (filename.contains(".jpg") || filename.contains(".png") || filename.contains(".jpeg")) {
                    System.out.println("Loading image ...");
                    String lenStr = in.readUTF();
                    int len = Integer.parseInt(lenStr);
                    byte[] imgByte = new byte[len];
                    in.readFully(imgByte);
                    ImageIcon img = new ImageIcon(imgByte);
                    JLabel labelImg = new JLabel(img);
                    JFrame f = new JFrame();
                    f.setBounds(new Rectangle(200, 200, 800, 600));
                    f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    f.add(labelImg);
                    f.setVisible(true);
                }

                /* VID */
                else if (filename.contains(".mkv")) {
                    System.out.println("Loading video ...");
                    // File temp = new File("assets/video/temp.mkv");
                    File temp = File.createTempFile("temp", "mkv");
                    FileOutputStream out = new FileOutputStream(temp);
                    Thread write = new Thread(new Writer(in, out));
                    write.start();
                    EmbeddedMediaPlayerComponent component = new EmbeddedMediaPlayerComponent();
                    JFrame f = new JFrame();
                    f.setContentPane(component);
                    f.setBounds(new Rectangle(200, 200, 800, 600));
                    f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    f.addWindowListener(new VideoListener(component, temp));
                    Thread.sleep(2000);
                    f.setVisible(true);
                    component.mediaPlayer().media().play(temp.toPath().toString());
                }
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void play(byte[] data)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(data));
        Player player = new Player(in);
        player.play();
    }

}
