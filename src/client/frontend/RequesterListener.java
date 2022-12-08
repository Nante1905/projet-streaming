package client.frontend;

import java.awt.event.*;
import java.net.Socket;

import javax.swing.JButton;

import client.RequesterThread;

public class RequesterListener implements MouseListener {

    Socket server;
    Thread requesterCurrent;

    public RequesterListener(Socket server) {
        this.server = server;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        JButton btn = (JButton) e.getSource();
        String query = btn.getActionCommand();
        if(requesterCurrent == null) {
            System.out.println("null le requester");
        }
        while(this.requesterCurrent != null && this.requesterCurrent.isInterrupted()) {
            this.requesterCurrent.interrupt();
            System.out.println("INTERRUPTED");
        }
        Thread t = new Thread(new RequesterThread(this.server, query));
        this.requesterCurrent = t;
        t.start();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
