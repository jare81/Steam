/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

import java.io.File;
import java.util.ArrayList;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

/**
 *
 * @author Dell
 */
public class Admin extends Usuario{
    private ArrayList<Musica>songs;
    private Reproductor rep;
    
    public Admin(String username, String pass, String fecha, boolean activo, String descripcion, String foto) {
        super("admin", "admin", fecha, activo, descripcion, foto);
        rep=new Reproductor();
        songs = rep.getArray();
    }
    
    
    
    public void agregarCancion(String rutaArchivo, String titulo, String artista, String album, String rutaPortada) {
    try {
        // Verificar si el archivo existe y es un MP3
        File archivo = new File(rutaArchivo);
        if (!archivo.exists() || !rutaArchivo.endsWith(".mp3")) {
            System.err.println("El archivo no existe o no es un archivo MP3 válido.");
            return;
        }

        // Editar los metadatos del archivo
        rep.editar(titulo, artista, album, rutaPortada, rutaArchivo);

        // Leer los atributos del archivo para verificar que los cambios sean correctos
        AudioFile audioFile = AudioFileIO.read(archivo);
        Tag tag = audioFile.getTag();

        String duracion = String.valueOf(audioFile.getAudioHeader().getTrackLength());
        int dur = Integer.parseInt(duracion);
        int min = dur / 60;
        int seg = dur % 60;

        System.out.println("Canción añadida:");
        System.out.println("Título: " + tag.getFirst(FieldKey.TITLE));
        System.out.println("Artista: " + tag.getFirst(FieldKey.ARTIST));
        System.out.println("Álbum: " + tag.getFirst(FieldKey.ALBUM));
        System.out.println("Duración: " + min + ":" + seg);

        // Crear la instancia de Musica
       
        // Agregar la canción al ArrayList
        //songs.add(nuevaCancion);
        System.out.println("La canción ha sido añadida correctamente al reproductor.");
    } catch (Exception e) {
        System.err.println("Error al agregar la canción: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    
}
