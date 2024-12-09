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
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.FieldKey;
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
            System.out.println("Indice fuera de rango.");
            return;
        }

        Musica cancion = songs.get(indice);
        System.out.println("Reproduciendo: " + cancion.getTitulo() + " - " + cancion.getArtista());
        reproducir(cancion.getRuta());
    }

    public void mostrarInformacion(int indice) {
        if (indice < 0 || indice >= songs.size()) {
            System.out.println("indice fuera de rango.");
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

    public void agregarCancion(String titulo, String artista, String album, String rutaOriginal, String portadaOriginal) {
        try {
            File archivoMP3 = new File(rutaOriginal);
            File archivoPortada = new File(portadaOriginal);

            // Verificar que el archivo MP3 exista y sea válido
            if (!archivoMP3.exists() || !archivoMP3.getName().endsWith(".mp3")) {
                System.out.println("El archivo MP3 no es válido.");
                return;
            }

            //editar(titulo, artista, album, rutaOriginal, portadaOriginal);
            // Verificar que el archivo de portada exista y sea válido
            if (!archivoPortada.exists() || (!archivoPortada.getName().endsWith(".png") && !archivoPortada.getName().endsWith(".jpg"))) {
                System.out.println("El archivo de portada no es válido.");
                return;
            }

            AudioFile audioFile = AudioFileIO.read(archivoMP3);
            int duracionSegundos = audioFile.getAudioHeader().getTrackLength();
            int minutos = duracionSegundos / 60;
            int segundos = duracionSegundos % 60;
            String duracion = String.format("%02d:%02d", minutos, segundos);

            File destinoMP3 = new File(songsDir, archivoMP3.getName());

            File destinoPortada = new File(songsDir, archivoPortada.getName());
            editar(titulo, artista, album, rutaOriginal, portadaOriginal);

            Files.copy(archivoMP3.toPath(), destinoMP3.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(archivoPortada.toPath(), destinoPortada.toPath(), StandardCopyOption.REPLACE_EXISTING);
            songs.add(new Musica(titulo, artista, album, duracion, destinoMP3.getPath(), destinoPortada.getPath()));
            System.out.println("Canción agregada exitosamente:");
            System.out.println("Título: " + titulo);
            System.out.println("Artista: " + artista);
            System.out.println("Álbum: " + album);
            System.out.println("Duración: " + duracion);

        } catch (Exception e) {
            System.out.println("Error al agregar la canción: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void editar(String titulo, String artista, String album, String ruta, String rutaPortada) {
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

    public void cargaInicial() {
        try {
            File[] archivos = songsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
            if (archivos == null || archivos.length == 0) {
                System.out.println("No se encontraron archivos MP3 en la carpeta.");
                return;
            }

            for (File archivoMP3 : archivos) {
                try {
                    // Leer el archivo MP3 y obtener metadatos
                    AudioFile audioFile = AudioFileIO.read(archivoMP3);
                    Tag tag = audioFile.getTag();

                    String titulo = tag != null ? tag.getFirst(FieldKey.TITLE) : "Desconocido";
                    String artista = tag != null ? tag.getFirst(FieldKey.ARTIST) : "Desconocido";
                    String album = tag != null ? tag.getFirst(FieldKey.ALBUM) : "Desconocido";
                    int duracionSegundos = audioFile.getAudioHeader().getTrackLength();
                    int minutos = duracionSegundos / 60;
                    int segundos = duracionSegundos % 60;
                    String duracion = String.format("%02d:%02d", minutos, segundos);

                    // Buscar una portada asociada (si existe un archivo de imagen con el mismo nombre que el MP3)
                    String rutaPortada = "Sin portada";
                    if (tag != null && tag.getFirstArtwork() != null) {
                        Artwork artwork = tag.getFirstArtwork();
                        byte[] imagenBytes = artwork.getBinaryData();

                        // Guardar la portada en la carpeta de canciones
                        File archivoPortada = new File(songsDir, archivoMP3.getName() + "_cover.jpg");
                        try (FileOutputStream fos = new FileOutputStream(archivoPortada)) {
                            fos.write(imagenBytes);
                            rutaPortada = archivoPortada.getAbsolutePath();
                        }
                    }

                    // Agregar canción al arreglo
                    songs.add(new Musica(
                            titulo.isEmpty() ? "Desconocido" : titulo,
                            artista.isEmpty() ? "Desconocido" : artista,
                            album.isEmpty() ? "Desconocido" : album,
                            duracion,
                            archivoMP3.getPath(),
                            rutaPortada
                    ));
                } catch (Exception e) {
                    System.err.println("Error al cargar el archivo " + archivoMP3.getName() + ": " + e.getMessage());
                }
            }

            System.out.println("Canciones cargadas exitosamente: " + songs.size());
        } catch (Exception e) {
            System.err.println("Error durante la carga inicial: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
    
}

 /* public void cargaInicial() {
        agregarCancion("Run", "One Republic", "Human", "src/Canciones/Run.mp3", "src/Canciones/Run.png");
        agregarCancion("Mente en Blanco", "K4OS", "Single", "src/Canciones/MENTE EN BLANCO.mp3", "src/Canciones/MENTE EN BLANCO.png");
        agregarCancion("Can't Remember to Forget You", "Shakira", "Single", "src/Canciones/Can't Remember to Forget You.mp3", "src/Canciones/Can't Remember to Forget You.png");
        agregarCancion("APT", "Rose & Bruno Mars", "Single", "src/Canciones/APT.mp3", "src/Canciones/APT.png");
        agregarCancion("Back To Me", "The Rose", "Alive", "src/Canciones/Back To Me.mp3", "src/Canciones/Back To Me.png");
        agregarCancion("Bad Liar", "One Republic", "Origins", "src/Canciones/Bad Liar.mp3", "src/Canciones/Bad Liar.png");
        agregarCancion("Born For This", "The Score", "Pressure", "src/Canciones/Born For This.mp3", "src/Canciones/Born For This.png");
        agregarCancion("Pink Venom", "Black Pink", "Born Pink", "src/Canciones/Pink Venom.mp3", "src/Canciones/Pink Venom.png");
        agregarCancion("Save Your Tears", "The Weeknd", "After Hours", "src/Canciones/Save Your Tears.mp3", "src/Canciones/Save Your Tears.png");
        agregarCancion("True Colors", "Anna Kendrick y Justin Timberlake", "Single", "src/Canciones/True Colors.mp3", "src/Canciones/True Colors.png");
    }*/
    
