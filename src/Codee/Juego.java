package Codee;

import java.util.Date;

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
    private String lanzamiento; 
    private String ruta;
    private String foto;
    
    
    public Juego(String nombre, String genero, String desarrollador, String lanzamiento, String ruta, String foto){
        this.nombre=nombre;
        this.genero=genero;
        this.desarrollador=desarrollador;
        this.lanzamiento=lanzamiento;
        this.ruta =ruta;
        this.foto=foto;
        
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public String getLanzamiento() {
        return lanzamiento;
    }

    public String getRuta() {
        return ruta;
    }
    public String getFoto() {
        return foto;
    }
    
}
