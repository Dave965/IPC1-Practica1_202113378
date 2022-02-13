/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pac.manus;

import java.util.Random;
/**
 *
 * @author dabra
 */
public class Tablero {
    public Jugador pj;
    public int punteo, movimientos;
    public final int ancho, alto;
    public String[][] paredes; 
    private final Random rand = new Random();
    public Pacmanus p;
    
    public void tab(){     
        //Crear las paredes
        String [][] tablero = new String [alto][ancho];
        for(int i=0;i<alto;i++){
            tablero[i][0] = "*";
            tablero[i][ancho-1] = "*";
        }
        for(int i=0;i<ancho;i++){
            tablero[0][i] = "*";
            tablero[alto-1][i] = "*";
        }
        
        //poner salidas laterales
        if(alto%2!=0){
            for(int i = 0;i<ancho;i++){
                tablero[(alto+1)/2-1][i]= " ";
            }
        } else{
            for(int i = 0;i<ancho;i++){
                tablero[(alto)/2-1][i]= " ";
                tablero[alto/2][0]= " ";
            }
        }
        
        //generar paredes
        
        for(int i = 0; i<alto;i++){
           for(int j = 0; j<ancho;j++) {
               if(tablero[i][j] == null){
                   int check = rand.nextInt(11);
                   if(check<2){
                       tablero[i][j] = "*";
                   }else{
                       tablero[i][j] = " ";
                   }
               }else if(!tablero[i][j].equals("*") && !tablero[i][j].equals(" ")){
                   int check = rand.nextInt(11);
                   if(check<2){
                       tablero[i][j] = "*";
                   }
               }
           }
        }
        
        this.paredes = tablero;
        
        poner_Pacmanus();
        
        poner_consumible("#",-10);
        poner_consumible("$",15);
        poner_consumible("@",10);
        
        punteo = 10;
        movimientos = 0;
        
    } 
    
    public void update_frame(){
        for(int i = 0; i<alto;i++){
           for(int j = 0; j<ancho;j++) {
               if("V".equals(paredes[i][j])){
                   paredes[i][j] = " ";
                   break;
               }
           }
        }
        if(p.y>ancho-1){
            p.setY(0);
        }
        if(p.y<0){
            p.setY(ancho-1);
        }
        switch (paredes[p.x][p.y]) {
            case "#" -> {
                punteo = punteo-10;
                poner_consumible("#",-10);
            }
            case "$" -> {
                punteo = punteo+15;
                poner_consumible("$",15);
            }
            case "@" -> {
                punteo = punteo+10;
                poner_consumible("@",10);
            }
            default -> {
            }
        }
        pj.setPunteo(punteo);
        pj.setMovimientos(movimientos);
        paredes[p.x][p.y] = "V";
    }
    
    public void poner_Pacmanus(){
        int x = rand.nextInt(alto);
        int y = rand.nextInt(ancho);
        
        if(!paredes[x][y].equals(" ")){
            poner_Pacmanus();
        }else{
            Pacmanus pac = new Pacmanus(x,y);
            this.p = pac;
            paredes[x][y] = p.signo;
        }
      
    }
    
    public void poner_consumible(String simbolo, int puntaje){
        int x = rand.nextInt(alto);
        int y = rand.nextInt(ancho);
        if(!paredes[x][y].equals(" ")){
            poner_consumible(simbolo,puntaje);
        }else{
            Consumibles con = new Consumibles(simbolo,puntaje,x,y);
            paredes[x][y] = con.simbolo; 
        }
    }
    
    public String frame(){
        String tab_comp = "Nombre: "+pj.getNombre()+"\n"+
                "Punteo: "+punteo+
                "\n"+ "Movimientos: "+ movimientos+"\n";
        for(int i = 0; i<alto;i++){
           for(int j = 0; j<ancho;j++) {
               if(paredes[i][j] == null){
                   tab_comp += " ";
               }else{
                   tab_comp += paredes[i][j];
               }
           }
           tab_comp += "\n";
        }
        return tab_comp;
    }

    public Tablero(int ancho, int alto, Jugador pj) {
        this.ancho = ancho;
        this.alto = alto;
        this.pj = pj;
    }   
}
