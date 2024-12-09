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
public class Juego extends ArchivoMultimedia {
    private String nombre;
    private String genero;
    private String desarrollador;
    private String lanzamiento; 
    private String ruta;
    private String foto;
    
    
    public Juego(String nombre, String genero, String desarrollador, String lanzamiento, String ruta, String foto){
        super(nombre, ruta, foto);
        this.nombre=nombre;
        this.genero=genero;
        this.desarrollador=desarrollador;
        this.lanzamiento=lanzamiento;
        this.ruta =ruta;
        this.foto=foto;
        
    }

    @Override
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

    @Override
    public String getRuta() {
        return ruta;
    }
    @Override
    public String getFoto() {
        return foto;
    }

    @Override
    public void ver() {
        System.out.println("Iniciando juego: " + getNombre());
    }
    
}
