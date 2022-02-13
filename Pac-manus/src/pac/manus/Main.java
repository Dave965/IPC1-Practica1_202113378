/*
 */
package pac.manus;
import java.util.Scanner;
import java.io.IOException;


/**
 *
 * @author dabra
 */
public class Main {
    private String nombre;
    private int edad;
    private int ancho;
    private int alto;
    private Historial h;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        Main app = new Main();
        app.h = new Historial();
        app.menu(app,input);          
    } 
    
    public void menu(Main app, Scanner input) throws IOException,InterruptedException{
        app.limpiar();
        app.mostrar("""
                ****************
                * 1.Jugar      *
                * 2.Historial  *
                * 3.Salir      *
                ****************
                """);
        app.mostrar("Elige una opcion: ");
        String selec = input.nextLine();
        boolean check = false; 
        switch(selec){
            case "1" -> app.jugar(app,check,input);
            case "2" -> app.historial(app, input);
            case "3" -> app.salir();
            default -> {
                app.mostrar("Elige una opcion valida!");
                input.nextLine();
                app.menu(app, input);
           }
            
        }
    }
    
    public void jugar(Main app, boolean check, Scanner input) throws IOException, InterruptedException{
        app.rev_nombre(app, input);
        app.rev_edad(app, input);
        app.rev_dimensiones(app, input);
        
        Jugador new_pj = new Jugador(this.nombre,this.edad);
        Tablero n_juego = new Tablero(ancho,alto,new_pj);
                
        n_juego.tab();
                
        Pacmanus jug = n_juego.p; 
        app.limpiar();
        app.mostrar(n_juego.frame());
        while(!check){
            app.mostrar("Cual es tu siguiente movimiento?: ");
            char caracter = (char)System.in.read();
            switch(caracter){
                case 115 -> {
                    n_juego.movimientos++;
                    jug.setX(jug.getX()+1);
                    if(app.rev_lin(n_juego, jug,-1)){
                        jug.setX(jug.getX()-1);
                    }
                    n_juego.update_frame();
                }
                        
                case 119 -> {
                    n_juego.movimientos++;
                    jug.setX(jug.getX()-1);
                    if(app.rev_lin(n_juego, jug,+1)){
                        jug.setX(jug.getX()+1);
                    }
                    n_juego.update_frame();
                }
                        
                case 97 -> {
                    n_juego.movimientos++;
                    jug.setY(jug.getY()-1);
                    if(app.rev_col(n_juego, jug,+1)){
                        jug.setY(jug.getY()+1);
                    }
                    n_juego.update_frame();
                }
                        
                case 100 -> {
                    n_juego.movimientos++;
                    jug.setY(jug.getY()+1);
                    if(app.rev_col(n_juego, jug,-1)){
                        jug.setY(jug.getY()-1);
                    }
                    n_juego.update_frame();
                }
                case 109 -> {
                    check=true;
                }
            }
            app.limpiar();
            app.mostrar(n_juego.frame());
            if(!check){
                check = app.acabado(n_juego); 
            }
        }
        if(h.tamano == 0){
            h.poner_primero(new_pj);
        }else{
            h.poner_siguiente(new_pj);
        }
        if(check){
            if(n_juego.punteo >= 100){
                mostrar("Ganaste!\n");
                input.nextLine();
                input.nextLine();
            }else{
                mostrar("Intentalo de nuevo :(\n");
                input.nextLine();
                input.nextLine();
            }
        }
        app.limpiar();
        app.menu(app, input);
    }
    
    public boolean rev_col(Tablero t,Pacmanus p,int rev){
        if(p.y<=t.ancho-1 && p.y>=0){
            return t.paredes[p.x][p.y].equals("*");
        }else return t.paredes[p.x][p.y+rev].equals("*");
        
    }
    
    public boolean rev_lin(Tablero t,Pacmanus p, int rev){
        if(p.x<=t.alto-1 && p.x>=0){
            return t.paredes[p.x][p.y].equals("*");
        }else return t.paredes[p.x+rev][p.y].equals("*");
        
    }
    
    public void salir(){
        System.exit(0);
    }
    
    public void historial(Main app,Scanner input)throws IOException, InterruptedException{
        app.limpiar();
        h.imprimir();
        input.nextLine();
        app.menu(app,input);
        
    }
    
    public void rev_nombre(Main app,Scanner input)throws IOException{
        boolean check = true;
        app.mostrar("Escribe tu nombre: ");
        this.nombre = input.nextLine();
        //referencia de internet para validacion de caracteres: https://parzibyte.me/blog/2020/02/26/java-cadena-tiene-solo-letras/
        for (int i = 0; i < nombre.length(); i++) {
            char c = nombre.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                 check = false;
            }
        }
        
        if(!check){
            app.mostrar("Por favor, escribe un nombre con solo letras. (pulsa enter para continuar)");
            input.nextLine();
            app.rev_nombre(app,input);
        } 
    }
    
    public void rev_edad(Main app, Scanner input)throws IOException{
        app.mostrar("Escribe tu edad: ");
        try{
            this.edad = Integer.parseInt(input.nextLine());
        }catch(NumberFormatException e){
            app.mostrar("Por favor, escribe tu edad con solo numeros. (pulsa enter para continuar)");
            input.nextLine();
            app.rev_edad(app, input);
        }
    }
    
    public void rev_dimensiones(Main app, Scanner input)throws IOException{
        try{
            app.mostrar("Alto del tablero: ");        
            this.alto = Integer.parseInt(input.nextLine());
            app.mostrar("Ancho del tablero: ");
            this.ancho = Integer.parseInt(input.nextLine());
        }catch(NumberFormatException e){
            app.mostrar("Por favor, escribe las dimensiones con solo numeros. (pulsa enter para continuar)");
            input.nextLine();
            app.rev_dimensiones(app, input);
        }
        if(alto <8 || ancho <8){
            app.mostrar("Las dimensiones deben ser mayores a 8x8. (pulsa enter para continuar)");
            input.nextLine();
            app.rev_dimensiones(app, input);
        }
    }
    
    public boolean acabado(Tablero t){
        return t.punteo>=100 || t.punteo<=0;
    }
    
    public void limpiar()throws IOException, InterruptedException{
        //limpiar la consola, codigo conseguido en https://stackoverflow.com/questions/2979383/how-to-clear-the-console
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); 
    }
    
    public void mostrar(String s){
        System.out.print(s);
    }
}
