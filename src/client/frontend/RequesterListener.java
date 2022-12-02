package client.frontend;

import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;

import client.RequesterThread;

public class RequesterListener implements MouseListener {

    Socket server;

    public RequesterListener(Socket server) {
        this.server = server;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        JButton btn = (JButton) e.getSource();
        String query = btn.getActionCommand();
        Thread t = new Thread(new RequesterThread(this.server, query));
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
