/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientbomberman;

import com.google.gson.Gson;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author lduarte
 */
public class ClientBomberman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       /** Jugador j=new Jugador(1,1,1);
        ControlJugador control=new ControlJugador(j,"127.0.0.1",4000); 
        JFrame ventana=new JFrame(); 
        control.setFocusable(true);
        ventana.getContentPane().add(control);
        ventana.pack();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        control.jugar();**/
      Inicio i=new Inicio();
       i.setVisible(true);
      /* Gson gson = new Gson();
       Jugador j=new Jugador(1,1,1);
       Estado es=new Estado();
       EstadoClient e=new EstadoClient();
       e.setJugador(j);
       e.setTablero(es.getTablero());
       
       // 2. Java object to JSON, and assign to a String
       String jsonInString = gson.toJson(e);
       ClientUDP udp=new ClientUDP("172.20.7.209",4001);
       ClientUDP udp1=new ClientUDP("172.20.7.209",4002);
       for(int i1=0; i1<3; i1++){
           udp.sendData(jsonInString);
           udp1.sendData("HOLA ME LLAMO ALVARITO XDxDXd");
           System.out.println(udp.getData());
           
       }
       */
       
       
       //ventana.setSize(800, 800);
        
    }
    
}
