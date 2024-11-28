/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.IOException;
public class Musica {
    private String titulo;
    private String artista;
    private String album;
    private int duracion;
    private String ruta;
    
    public Musica(String titulo, String artista, String album, int duracion, String ruta){
        this.titulo = titulo;
        this.artista=artista;
        this.album=album;
        this.duracion=duracion;
        this.ruta=ruta;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public String getArtista(){
        return artista;
    }
    
    public String getAlbum(){
        return album;
    }
    
    public int getDuracion(){
        return duracion;
    }
    
    public String getRuta(){
        return ruta;
    }
    
    
    
    
}
