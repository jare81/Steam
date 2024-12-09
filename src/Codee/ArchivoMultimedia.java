/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

/**
 *
 * @author Dell
 */
public abstract class ArchivoMultimedia {
    private String nombre;
    private String ruta;
    private String foto;

    public ArchivoMultimedia(String nombre, String ruta, String foto) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.foto=foto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuta() {
        return ruta;
    }
    
    public String getFoto() {
        return foto;
    }

    public abstract void ver();
}
