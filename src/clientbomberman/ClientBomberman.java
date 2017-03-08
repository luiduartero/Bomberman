/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientbomberman;

/**
 *
 * @author lduarte
 */
public class ClientBomberman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ControlJugador j=new ControlJugador(null); 
        j.init();
        j.setVisible(true);
        //j.jugar();
        
    }
    
}
