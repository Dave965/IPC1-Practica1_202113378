/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pac.manus;

/**
 *
 * @author dabra
 */
public class Consumibles {
    
    public String simbolo;
    public int Puntaje;
    private int x;
    private int y;

    public Consumibles(String simbolo, int Puntaje, int x, int y) {
        this.simbolo = simbolo;
        this.Puntaje = Puntaje;
        this.x = x;
        this.y = y;
    }
    
    public Consumibles(Consumibles c,int x,int y){
        this.simbolo = c.simbolo;
        this.Puntaje = c.Puntaje;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
