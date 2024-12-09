/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;



public class Musica extends ArchivoMultimedia {
    private String titulo;
    private String artista;
    private String album;
    private String duracion;
    private String foto;
    private String ruta;
    
    public Musica(String titulo, String artista, String album, String duracion, String ruta, String foto){
        super(titulo, ruta, foto);
        this.titulo = titulo;
        this.artista=artista;
        this.album=album;
        this.duracion=duracion;
        this.ruta=ruta;
        this.foto=foto;
        
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
    
    @Override
    public String getRuta(){
        return ruta;
    }
    
    @Override
     public String getFoto() {
        return foto;
    }
   
     @Override
    public String toString() {
        return "Titulo: " + titulo + ", Artista: " + artista + ", album: " + album + ", Ruta: " + ruta;
    }

    @Override
    public void ver() {
        System.out.println("Reproduciendo canción: " + getNombre());
    }

    
    
    
    
    
}
