/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientbomberman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lduarte
 */
public class ClientUDP {
    private String ip; 
    private int puerto; 
    private DatagramSocket socket;
    private InetAddress address; 
 

    public ClientUDP(String ip,int puerto) {
         this.ip = ip;
         this.puerto=puerto; 
        try {
            socket = new DatagramSocket();
            address=InetAddress.getByName(ip);
        } catch (SocketException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getData() throws SocketException, IOException{
        //System.out.println("tratando de recibir");
        //socket.setSoTimeout(1000); 
        byte[] RecogerServidor_bytes = new byte[1024];
        DatagramPacket servPaquete = new DatagramPacket(RecogerServidor_bytes,1024);
        socket.setSoTimeout(1000);
        try {
            socket.receive(servPaquete);
        } catch (SocketTimeoutException e) {
           
        }
        String cadenaMensaje = new String(RecogerServidor_bytes).trim();
       // System.out.println("MEEEEEE");
        return cadenaMensaje; 
        
    }
    public void sendData(String json){
        //Envio de datos
        //System.out.println("enviando daos");
        DatagramPacket paquete = new DatagramPacket(json.getBytes(),json.length(),address,puerto);
        try {
            socket.send(paquete);
        } catch (IOException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("finalizado");
        
    }

    public void cerrarSesion(){ 
        socket.close();
    }
    
    
    
    
}
