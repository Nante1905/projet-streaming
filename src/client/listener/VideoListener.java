package client.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class VideoListener implements WindowListener {


    EmbeddedMediaPlayerComponent component;
    File tempfile;

    public VideoListener(EmbeddedMediaPlayerComponent component, File tempfile) {
        this.component = component;
        this.tempfile = tempfile;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        component.mediaPlayer().controls().stop(); 
        while(tempfile.exists()) {
            tempfile.delete();
        } 
        System.out.println("deleted");      
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
    
}
