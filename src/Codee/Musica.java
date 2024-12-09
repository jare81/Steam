/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;



public class Musica {
    private String titulo;
    private String artista;
    private String album;
    private String duracion;
    private String portada;
    private String ruta;
    
    public Musica(String titulo, String artista, String album, String duracion, String ruta, String portada){
        this.titulo = titulo;
        this.artista=artista;
        this.album=album;
        this.duracion=duracion;
        this.ruta=ruta;
        this.portada=portada;
        
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
    
    public String getDuracion(){
        return duracion;
    }
    
    public String getRuta(){
        return ruta;
    }
    
     public String getPortada() {
        return portada;
    }
   
     @Override
    public String toString() {
        return "Titulo: " + titulo + ", Artista: " + artista + ", album: " + album + ", Ruta: " + ruta;
    }

    
    
    
    
    
}
