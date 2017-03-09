/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientbomberman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author lduarte
 */
public class ServerUDP {
    //ServerSocket servidor;
    DatagramSocket socket;
    DatagramPacket entrada;
    ColaUDP cola;
    Estado estado;
    int max_jugadores = 4;
    
    /**
     * @param args the command line arguments
     */
    public ServerUDP() throws IOException{
        byte [] datos_entrada = new byte[1024];
        
        System.out.println("Servidor iniciando...");
        
        socket = new DatagramSocket(4000);
       
        entrada = new DatagramPacket(datos_entrada, datos_entrada.length);
        
        cola = new ColaUDP(max_jugadores);
        
        for(int i=0; i<max_jugadores; i++){
            RequestServerUDP request = new RequestServerUDP(cola);
            Thread thread = new Thread(request);
            thread.start();
            
        }
        
        while(true){
            
            //Socket socket = servidor.accept();
            //socket.receive(entrada);
            cola.put(socket);
            
        }
        
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        // TODO code application logic here
        
        ArrayList<Jugador> clientes = new ArrayList<Jugador>();
              
         
        
        System.out.println("Iniciando Servidor");
        
        ServerUDP server = new ServerUDP();
        
        
        
        
        
//        String cadena; 
//        String cadena1="";
//        FileReader f = new FileReader("configuracion.txt");
//        try (BufferedReader b = new BufferedReader(f)) {
//            while((cadena = b.readLine())!=null) {
//                cadena1=cadena1+cadena;
//            }
//        }
//        
//        //parseo 
//        String[] result= cadena1.split("\n");
//        String linea1=result[0];
//        String []linea1partes=linea1.split("<");
//        String threadsc=linea1partes[1].substring(0, 1);
//        System.out.println(threadsc);
//        
//        
//        System.out.println("Iniciando los threads");
//        
//
//
//        System.out.println("Iniciando Servidor");

    }
}
