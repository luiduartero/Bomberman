/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientbomberman;

import com.google.gson.Gson;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class ControlJugador extends JPanel implements ActionListener, KeyListener{
    private Jugador jugador; 
    private ArrayList<Bomba> bombas;
    private ArrayList<Fire> fire; 
    private ArrayList<Poder> poderes; 
    private Estado estado; 
    private int time=0; 
    private ClientUDP client;
    private Gson gson= new Gson();
    
    
    public ControlJugador(Jugador jugador, String ip,int puerto) throws IOException {
        //System.out.println("yo ya estoy");
        this.jugador = jugador;
        client=new ClientUDP(ip,puerto);
        bombas= new ArrayList(); 
        fire= new ArrayList(); 
        poderes= new ArrayList(); 
        estado=new Estado();
        time=estado.getTiempo();
        this.setPreferredSize(new Dimension(800,800));
        addKeyListener(this); 
        setFocusable(true);
        //jugar();
    }

    @Override
      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D d = (Graphics2D) g;
        bombas(g);
        player(g);
        map(g);
         for(int i=0;i<bombas.size();i++){
                System.out.println(bombas.size());
                if(bombas.get(i).getExplodeTime()<time){
                    System.out.println("siiuuuuuuuu");
                   explotaBomba(bombas.remove(i));
                }
         }
        fires(g);
        repaint();
    }

    public void player(Graphics g) {
        try {
            BufferedImage pImg = ImageIO.read(new File("bomber_2.png"));
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
    public void fires(Graphics g){
        for(int i=0; i<fire.size();i++){
            try {
                BufferedImage pImg = ImageIO.read(new File("fire.gif"));
                g.drawImage(pImg, fire.get(i).getX()*40,fire.get(i).getY()*40, null);
                fire.remove(i);
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
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("EPPPAA");
         for(int i=0;i<bombas.size();i++){
                System.out.println(bombas.size());
                if(bombas.get(i).getExplodeTime()<time){
                    System.out.println("siiuuuuuuuu");
                   explotaBomba(bombas.remove(i));
                }
         }
        repaint();
    }
    /**
    public void jugar() throws IOException{
        while (jugador.isVivo()){
            //System.out.println("worrkkkkkkkk");
            //Revizar lo que me mandaron del server
            /**String json=client.getData();
            if(!"".equals(json)){
                estado = gson.fromJson(json, Estado.class);
            }
            //covertir a clase
            
            //Sacar jugador 
            
            //Tomar listas de valores
            
            
            //revisar si he realizado acciones
                //mandar la nueva info al server
                
            //hacer una instancia 
            //convertir a json 
            //mandarlo 
                
         
           
            }**/
          //repaint();
            
        //}
    
    public void generarPoder(int x, int y){
        //Generar Random para ver si lo ponemos o no 
        //Generar Random para el tipo 
    }
    
    public void revizarPaso(int x, int y){
        
    }
    
    public void explotaBomba(Bomba b){
        int [][] tablero=estado.getTablero();
        int potencia=b.getPotencia(); 
        int x=b.getX();
        int y=b.getY();
        for(int i=1;i<=potencia;i++){
            try{
                if(tablero[x-i][y]!=0){
                    fire.add(new Fire(x-i,y));
                    if(tablero[x-i][y]==1){
                        tablero[x-i][y]=-1;
                       
                    }
                }
                
            }catch(Exception e){
            }
            try{
                if(tablero[x][y-i]!=0){
                    fire.add(new Fire(x,y-i));
                    if(tablero[x][y-i]==1){
                        tablero[x][y-i]=-1;
                    }
                }
                
            }catch(Exception e){
            }
             try{
                if(tablero[x+i][y]!=0){
                    fire.add(new Fire(x+i,y));
                    if(tablero[x+i][y]==1){
                        tablero[x+i][y]=-1;
                    }
                }
                
            }catch(Exception e){
            }
            try{
                if(tablero[x][y+i]!=0){
                    fire.add(new Fire(x,y+i));
                    if(tablero[x][y+i]==1){
                        tablero[x][y+i]=-1;
                    }
                }
                
            }catch(Exception e){
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println("haaaaaaadasdasd");
            switch (key) {
                case KeyEvent.VK_LEFT:
                    System.out.println("haaaaaaadasdasd");
                    time++;
                    int x=jugador.getX();
                    if(x>0){
                        if(estado.getTablero()[x-1][jugador.getY()]==-1){
                            jugador.MoveIzquierda();
                        }
                    }
                    super.repaint();
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("haaaaaaadasdasd");
                    time++;
                    int x1=jugador.getX();
                    if(x1<19){
                        if(estado.getTablero()[x1+1][jugador.getY()]==-1){
                            jugador.MoveDerecha();
                        }
                    }
                    super.repaint();
                    break;
                case KeyEvent.VK_UP:
                    time++;
                    int y=jugador.getY();
                    if(y>0){
                        if(estado.getTablero()[jugador.getX()][y-1]==-1){
                            jugador.MoveArriba();
                        }
                    }
                    super.repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    time++;
                    int y1=jugador.getY();
                    if(y1<19){
                        if(estado.getTablero()[jugador.getX()][y1+1]==-1){
                            jugador.MoveAbajo();
                        }
                    }
                    super.repaint();
                    break;
                case KeyEvent.VK_SPACE:
                    Bomba bombina=new Bomba(jugador.getId(),jugador.getX(),jugador.getY(),jugador.getPotenciaB());
                    bombina.setExplodeTime(time+5);
                    bombas.add(bombina);
                    super.repaint();
                    break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    
    }
}
   
    
    
   
    
    

