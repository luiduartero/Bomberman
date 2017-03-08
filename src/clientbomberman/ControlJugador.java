/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientbomberman;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lduarte
 */
public class ControlJugador extends JFrame implements KeyListener{
    private Jugador jugador; 
    private ArrayList<Bomba> bombas;
    private Estado estado; 

    public ControlJugador(Jugador jugador) {
        this.jugador = jugador;
        bombas= new ArrayList(); 
        estado=new Estado();
        this.setSize(800,800);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    public void init(){
        GridLayout gL = new GridLayout(20, 20);
        JPanel panel = new JPanel(gL);
        int [][] tablero=estado.getTablero();
        JLabel [][] icons=new JLabel[tablero.length][tablero.length];
        for(int i=0; i<tablero.length;i++){
            for(int j=0;j<tablero.length;j++){
                if(tablero[j][i]==-1){
                    ImageIcon image=new ImageIcon("camino.png");
                    icons[j][i]=new JLabel(image);
                    icons[j][i].setSize(40, 40);
                }
                if(tablero[j][i]==0){
                    ImageIcon image=new ImageIcon("pared.png");
                    icons[j][i]=new JLabel(image);
                    icons[j][i].setSize(40, 40);
                }
                if(tablero[j][i]==1){
                    ImageIcon image=new ImageIcon("pared1.png");
                    icons[j][i]=new JLabel(image);
                    icons[j][i].setSize(40, 40);
                }
            }
        }
         for(int i=0; i<tablero.length;i++){
            for(int j=0;j<tablero.length;j++){
                panel.add(icons[j][i]);
            }
         }
        this.add(panel);
        
    }
    
    public void jugar() throws IOException{
        while (true){
            int caracter=System.in.read();
            char cara=(char)caracter;
            if(cara=='j'){
                System.out.println("meee");
            }
            System.out.println((char)caracter);
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    
                    
                    break;
                case KeyEvent.VK_RIGHT:
                    
                    break;
                case KeyEvent.VK_UP:
                    
                    break;
                case KeyEvent.VK_DOWN:
                   
                    break;
                case KeyEvent.VK_SPACE:
                    
                    break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
   
    
    
   
    
    

