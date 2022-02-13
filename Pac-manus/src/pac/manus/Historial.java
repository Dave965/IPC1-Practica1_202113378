/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pac.manus;
/**
 *
 * @author dabra
 */
public class Historial {
    public int tamano;
    private Jugador cabeza;

    public Historial() {
        tamano = 0;
        cabeza = null;
    }
    
    public void poner_primero(Jugador pj){
        this.cabeza = pj;
        tamano++;
    }
    
    public void poner_siguiente(Jugador pj){
        Jugador ahora = this.cabeza;
        for(int i=0; i<tamano; i++){
            ahora = ahora.getSig();
        }
        ahora.setSig(pj);
        tamano++;
    }
    
    public void imprimir(){
        System.out.println("******Historial******\n");
        if(tamano == 0){
            System.out.println("No se han registrado juegos");
        }else {
            Jugador ahora = this.cabeza;
            ahora.imprimir_datos();
            for(int i=1; i<tamano; i++){
                ahora = ahora.getSig();
                ahora.imprimir_datos();
            } 
        }     
    }
}
