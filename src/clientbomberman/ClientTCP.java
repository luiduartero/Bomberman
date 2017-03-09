/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientbomberman;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author lduarte
 */
public class ClientTCP {
    private String ip; 

    public ClientTCP(String ip) {
        this.ip = ip;
    }

    public ClientTCP() {
    }
    
    
    
    public void init() throws IOException{
        //Loading
        
        //URL url = ClientTCP.class.getResource("loading_1.gif");
        Icon icon = new ImageIcon("loading_1.gif");
        JLabel label = new JLabel(icon);
        
        JFrame f = new JFrame();
        f.getContentPane().add(label);
        f.setSize(800,800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        /////////////////////7
        Socket socket= new Socket(ip,12000);
        InputStream input=new DataInputStream(socket.getInputStream());
        InputStreamReader isr= new InputStreamReader(input);
        BufferedReader br= new BufferedReader(isr);
        
        String response = "";
        OutputStream os = socket.getOutputStream();
        // Send HELO command and get server response.
        response=""; 
        while(response.startsWith("HELO")==false){
            String command = "HELO\n";
            //System.out.print(command);
            os.write(command.getBytes());
            if(br.readLine()!=null){
                response = br.readLine();
            }
        }
        response=response.replace("HELO ", "");
        int id=Integer.parseInt(response);
        int x=1; 
        int y=1; 
        if(id==1){
            x=18;
        }if(id==2){
            y=18; 
        }if(id==3){
            x=18; 
            y=18;
        }
        Jugador j=new Jugador(id,x,y); 
        while (response.startsWith("START")){
            if(br.readLine()!=null){
                response = br.readLine();
            }
        }
        socket.close();
        f.setVisible(false);
        //GET EL PRIMER ESTADO
        ControlJugador control=new ControlJugador(j,"127.0.0.1"); 
        JFrame ventana=new JFrame(); 
        control.setFocusable(true);
        ventana.getContentPane().add(control);
        ventana.pack();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        control.jugar();
        //
        
    }
    
}
