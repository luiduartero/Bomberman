/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientbomberman;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lduarte
 */
public class ControlJugador extends JPanel implements KeyListener{
    private Jugador jugador; 
    private ArrayList<Bomba> bombas;
    private Estado estado; 

    public ControlJugador(Jugador jugador) {
        this.jugador = jugador;
        bombas= new ArrayList(); 
        estado=new Estado();
        this.setSize(800,800);
        this.addKeyListener(this);
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
    @Override
      public void paint(Graphics g) {
        Graphics2D d = (Graphics2D) g;
        super.paintComponent(g);
        bombas(g);
        player(g);
        map(g);
        repaint();
    }

    public void player(Graphics g) {
        try {
            BufferedImage pImg = ImageIO.read(new File("bomber.png"));
            g.drawImage(pImg, jugador.getX()*40, jugador.getY()*40, null);
        } catch (IOException e) {
        }
    }
    public void bombas(Graphics g){
        for(int i=0; i<bombas.size();i++){
            try {
                BufferedImage pImg = ImageIO.read(new File("bomba.png"));
                g.drawImage(pImg, bombas.get(i).getX()*40,bombas.get(i).getY()*40, null);
            } catch (IOException e) {
            
            }
        }
    }
    public void map(Graphics g) {
        //System.out.println("2dfsdf");
        Graphics2D d = (Graphics2D) g;
        int [][] tablero=estado.getTablero();
        for(int i=0; i<tablero.length;i++){
            for(int j=0;j<tablero.length;j++){
                if(tablero[j][i]==0){
                    try {
                        BufferedImage pImg = ImageIO.read(new File("pared.png"));
                        g.drawImage(pImg, j*40,i*40, null);
                    } catch (IOException ex) {
                        Logger.getLogger(ControlJugador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(tablero[j][i]==1){
                     try {
                        BufferedImage pImg = ImageIO.read(new File("pared1.png"));
                        g.drawImage(pImg, j*40,i*40, null);
                    } catch (IOException ex) {
                        Logger.getLogger(ControlJugador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    public void jugar() throws IOException{
        while (true){
          
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    System.out.println("leeeffft");
                    int x=jugador.getX();
                    if(x>0){
                        if(estado.getTablero()[x-1][jugador.getY()]==-1){
                            jugador.MoveIzquierda();
                        }
                    }
                    super.repaint();
                    break;
                case KeyEvent.VK_RIGHT:
                    int x1=jugador.getX();
                    if(x1<19){
                        if(estado.getTablero()[x1+1][jugador.getY()]==-1){
                            jugador.MoveDerecha();
                        }
                    }
                    super.repaint();
                    break;
                case KeyEvent.VK_UP:
                    int y=jugador.getY();
                    if(y>0){
                        if(estado.getTablero()[jugador.getX()][y-1]==-1){
                            jugador.MoveArriba();
                        }
                    }
                    super.repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    int y1=jugador.getY();
                    if(y1<19){
                        if(estado.getTablero()[jugador.getX()][y1+1]==-1){
                            jugador.MoveAbajo();
                        }
                    }
                    super.repaint();
                    break;
                case KeyEvent.VK_SPACE:
                    bombas.add(new Bomba(jugador.getId(),jugador.getX(),jugador.getY(),jugador.getPotenciaB()));
                    super.repaint();
                    break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    
    }
}
   
    
    
   
    
    

