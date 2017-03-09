/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientbomberman;

import javax.swing.JFrame;

/**
 *
 * @author lduarte
 */
public class JuegoFrame extends JFrame{

    public JuegoFrame(ControlJugador control) {
        getContentPane().add(control);
        pack();
        setSize(800,800);
        setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);     
    }
    
    
    
}
