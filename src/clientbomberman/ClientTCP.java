/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientbomberman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author lduarte
 */
public class ClientTCP {
    private String ip; 

    public ClientTCP(String ip) {
        this.ip = ip;
    }
    
    
    public void init() throws IOException{
        int paso=0; 
        //BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
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
        while (response.startsWith("START")){
            if(br.readLine()!=null){
                response = br.readLine();
            }
        }
        //GET EL PRIMER ESTADO
        //}
        socket.close();
        
    }
    
}
