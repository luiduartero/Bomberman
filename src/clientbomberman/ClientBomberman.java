/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientbomberman;

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
        Jugador j1=new Jugador(0,1,1);
        ControlJugador j=new ControlJugador(j1); 
        JFrame ventana=new JFrame(); 
        j.setFocusable(true);
        ventana.getContentPane().add(j);
        ventana.pack();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        j.jugar();
       //ventana.setSize(800, 800);
        
    }
    
}
