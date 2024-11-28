package Codee;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class Juego {
    private String nombre;
    private String genero;
    private String desarrollador;
    private String lanzamiento; //TIENE QUE SER FORMATO DATE
    private String ruta;
    
    
    public Juego(String nombre, String genero, String desarrollador, String lanzamiento, String ruta){
        this.nombre=nombre;
        this.genero=genero;
        this.desarrollador=desarrollador;
        this.lanzamiento=lanzamiento;
        this.ruta =ruta;
        
    }
    
}
