/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pac.manus;

/**
 *
 * @author dabra
 */
public class Jugador {
    
    private String nombre;
    private int edad;
    public int punteo;
    public int movimientos;
    public Jugador sig;

    public Jugador(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.sig = this;
        this.punteo = 10;
    }
    
    public void imprimir_datos(){
        System.out.println("Nombre: "+nombre);
        System.out.println("Edad: "+edad);
        System.out.println("Punteo: "+punteo);
        System.out.println("Movimientos: "+movimientos);
        System.out.println("********************");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPunteo() {
        return punteo;
    }

    public void setPunteo(int punteo) {
        this.punteo = punteo;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }
    
    public Jugador getSig() {
        return sig;
    }

    public void setSig(Jugador sig) {
        this.sig = sig;
    }
}
