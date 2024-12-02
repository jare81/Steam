/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;

public class Reproductor {

    private Player player;
    private FileInputStream fileInputStream;
    private BufferedInputStream bufferedInputStream;
    private Thread reproduccionThread;
    private ArrayList<Musica> songs;
    private File songsDir;
    private boolean estaSonando = false;

    public Reproductor() {
        songsDir = new File("src/Canciones");
        if (!songsDir.exists()) {
            songsDir.mkdir();
        }
        songs = new ArrayList<>();
        cargaInicial();
    }

    public ArrayList<Musica> getArray() {
        return songs;
    }

    public void listarCanciones() {
        System.out.println("Lista de canciones:");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i).toString());
        }
    }

    public void reproducir(String rutaArchivo) {
        detener();
        try {
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                System.err.println("El archivo no existe: " + rutaArchivo);
                return;
            }

            if (!rutaArchivo.endsWith(".mp3")) {
                System.err.println("El archivo debe ser un MP3");
                return;
            }

            fileInputStream = new FileInputStream(archivo);
            player = new Player(fileInputStream);

            new Thread(() -> {
                try {
                    player.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (Exception e) {
            System.err.println("Error al reproducir: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void reproducir(int indice) {
        if (indice < 0 || indice >= songs.size()) {
            System.out.println("Índice fuera de rango.");
            return;
        }

        Musica cancion = songs.get(indice);
        System.out.println("Reproduciendo: " + cancion.getTitulo() + " - " + cancion.getArtista());
        reproducir(cancion.getRuta());
    }

    public void mostrarInformacion(int indice) {
        if (indice < 0 || indice >= songs.size()) {
            System.out.println("Índice fuera de rango.");
            return;
        }

        Musica cancion = songs.get(indice);
        System.out.println("Información de la canción:");
        System.out.println("Título: " + cancion.getTitulo());
        System.out.println("Artista: " + cancion.getArtista());
        System.out.println("Álbum: " + cancion.getAlbum());
        System.out.println("Ruta: " + cancion.getRuta());
        System.out.println("Portada: " + cancion.getPortada());
    }

    private void cerrarRecursos() {
        try {
            if (player != null) {
                player.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
        }
    }

    public void detener() {
        try {
            if (player != null) {
                player.close();
                player = null;
            }
            if (fileInputStream != null) {
                fileInputStream.close();
                fileInputStream = null;
            }
        } catch (Exception e) {
            System.err.println("Error al detener la reproducción: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean estaSonando() {
        return estaSonando;
    }

    public void editar(String ruta, String titulo, String artista, String album, String rutaPortada) {
        try {
            File archivoMP3 = new File(ruta);

            AudioFile audioFile = AudioFileIO.read(archivoMP3);

            Tag tag = audioFile.getTag();

            tag.setField(FieldKey.TITLE, titulo);
            tag.setField(FieldKey.ARTIST, artista);
            tag.setField(FieldKey.ALBUM, album);
            File imagenPortada = new File(rutaPortada);
            Artwork portada = ArtworkFactory.createArtworkFromFile(imagenPortada);
            tag.deleteArtworkField();
            tag.setField(portada);

            audioFile.commit();

            System.out.println("Metadatos actualizados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al editar el archivo MP3: " + e.getMessage());
        }
    }

    public void leer(String ruta) {
        try {
            File archivoMP3 = new File(ruta);

            AudioFile audioFile = AudioFileIO.read(archivoMP3);

            // Obtener las etiquetas
            Tag tag = audioFile.getTag();

            String titulo = tag.getFirst(FieldKey.TITLE);
            String artista = tag.getFirst(FieldKey.ARTIST);
            String album = tag.getFirst(FieldKey.ALBUM);
            String duracion = String.valueOf(audioFile.getAudioHeader().getTrackLength());
            int dur = Integer.parseInt(duracion);
            int min = dur / 60;
            int seg = dur % 60;

            System.out.println("Título: " + (titulo.isEmpty() ? "Desconocido" : titulo));
            System.out.println("Artista: " + (artista.isEmpty() ? "Desconocido" : artista));
            System.out.println("Álbum: " + (album.isEmpty() ? "Desconocido" : album));
            System.out.println("Duración: " + min + ":" + seg);
            System.out.println("");

        } catch (Exception e) {
            System.out.println("Error al leer el archivo MP3: " + e.getMessage());
        }

    }

    public void listarCancionesUsuario(String username) {
        File carpetaUsuario = new File("src/users/" + username + "/musica");
        if (!carpetaUsuario.exists()) {
            JOptionPane.showMessageDialog(null, "La carpeta del usuario no existe o no contiene canciones.");
            return;
        }

        File[] archivos = carpetaUsuario.listFiles((dir, name) -> name.endsWith(".mp3"));
        if (archivos == null || archivos.length == 0) {
            System.out.println("No hay canciones en la carpeta del usuario.");
            return;
        }

        System.out.println("Canciones disponibles para " + username + ":");
        for (int i = 0; i < archivos.length; i++) {
            System.out.println((i + 1) + ". " + archivos[i].getName());
        }
    }

    public void reproducirCancionUsuario(String username, int indice) {
        File carpetaUsuario = new File("src/users/" + username + "/musica");
        if (!carpetaUsuario.exists()) {
            System.out.println("La carpeta del usuario no existe o no contiene canciones.");
            return;
        }

        File[] archivos = carpetaUsuario.listFiles((dir, name) -> name.endsWith(".mp3"));
        if (archivos == null || archivos.length == 0) {
            System.out.println("No hay canciones en la carpeta del usuario.");
            return;
        }

        if (indice < 0 || indice >= archivos.length) {
            System.out.println("Índice fuera de rango.");
            return;
        }
        
        String rutaArchivo = archivos[indice].getAbsolutePath();
        System.out.println("Reproduciendo: " + archivos[indice].getName());
        reproducir(rutaArchivo);
}
    
    
    
    public void cargaInicial() {
        songs.add(new Musica("Run", "One Republic", "Human", "src/Canciones/Run.mp3", "src/Canciones/Run.png"));
        songs.add(new Musica("Mente en Blanco", "K4OS", "Single", "src/Canciones/MENTE EN BLANCO.mp3", "src/Canciones/MENTE EN BLANCO.png"));
        songs.add(new Musica("Can't Remember to Forget You", "Shakira", "Single", "src/Canciones/Can't Remember to Forget You.mp3", "src/Canciones/Can't Remember to Forget You.png"));
        songs.add(new Musica("APT", "Rose & Bruno Mars", "Single", "src/Canciones/APT.mp3", "src/Canciones/APT.png"));
        songs.add(new Musica("Back To Me", "The Rose", "Alive", "src/Canciones/Back To Me.mp3", "src/Canciones/Back To Me.png"));
        songs.add(new Musica("Bad Liar", "One Republic", "Origins", "src/Canciones/Bad Liar.mp3", "src/Canciones/Bad Liar.png"));
        songs.add(new Musica("Born For This", "The Score", "Pressure", "src/Canciones/Born For This.mp3", "src/Canciones/Born For This.png"));
        songs.add(new Musica("Pink Venom", "Black Pink", "Born Pink", "src/Canciones/Pink Venom.mp3", "src/Canciones/Pink Venom.png"));
        songs.add(new Musica("Save Your Tears", "The Weeknd", "After Hours", "src/Canciones/Save Your Tears.mp3", "src/Canciones/Save Your Tears.png"));
        songs.add(new Musica("True Colors", "Anna Kendrick y Justin Timberlake", "Single", "src/Canciones/True Colors.mp3", "src/Canciones/True Colors.png"));
    }

    public static void main(String[] args) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        Reproductor reproductor = new Reproductor();

        reproductor.reproducir(1);

    }
}
