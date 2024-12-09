/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

import Codee.Musica;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

/**
 *
 * @author Dell
 */
public class Administrador {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Musica> musica;

    private Usuario usuarioActual;
    private Usuario us;
    private JuegoManager mm = new JuegoManager();
    private File listaUsers;
    private String nombre;
    private File bMusical;
    private File bJuegos;
    private ArrayList<Musica> songs;
    private ArrayList<Juego> games;

    public Administrador() {
        usuarios = new ArrayList<>();
        songs = new ArrayList<>();
        games = new ArrayList<>();

        usuarioActual = null;

        File directorio = new File("src/users/");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        cargarUsuarios();
        cargaInicial();
        cargarJuegos();

    }

    /////////////////////////// FUNCIONES DE USUARIOS GENERALES
    public void cargarUsuarios() {
        // usuarios.clear();

        File directorio = new File("src/users/");
        File[] carpetas = directorio.listFiles();

        if (carpetas != null) {
            for (File carpeta : carpetas) {
                if (carpeta.isDirectory()) {
                    String nombreUsuario = carpeta.getName();
                    File archivoUsuario = new File(carpeta, nombreUsuario + ".user");

                    if (archivoUsuario.exists()) {
                        try (RandomAccessFile archivo = new RandomAccessFile(archivoUsuario, "r")) {
                            String username = archivo.readUTF();
                            String pass = archivo.readUTF();
                            String fecha = archivo.readUTF();
                            boolean activo = archivo.readBoolean();
                            String descripcion = archivo.readUTF();
                            String foto = archivo.readUTF();

                            usuarios.add(new Usuario(username, pass, fecha, activo, descripcion, foto));
                        } catch (IOException e) {
                            System.out.println("Error al cargar usuario desde archivo: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Usuarios cargados: " + usuarios.size());
        }
    }

    public void guardarUsuarios() throws ClassNotFoundException {
        for (Usuario usuario : usuarios) {
            usuario.cargarArchivo();
        }
    }

    public boolean eliminarUsuario(String username) throws ClassNotFoundException {
        Usuario usuario = obtenerUsuario(username);
        if (usuario != null) {
            usuarios.remove(usuario);
            File archivoUsuario = new File("src/users/" + us.getUsername() + "/" + username + ".user");
            if (archivoUsuario.exists()) {
                archivoUsuario.delete();
            }
            guardarUsuarios(); // Actualizar archivo principal
            return true;
        }
        return false;
    }

    public final Usuario getUsuarioActual() {
        if (usuarioActual == null) {
            System.out.println("No hay usuario actual");
            return null;
        }
        return usuarioActual;
    }

    public Usuario obtenerUsuario(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    public ArrayList<Usuario> buscarUsuarios(String palabraClave) {
        ArrayList<Usuario> resultados = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().contains(palabraClave)) {
                resultados.add(usuario);
            }
        }
        return resultados;

    }

    public ArrayList<Usuario> getTodosUsuarios() {
        usuarioActual.cargarAmigos();
        return new ArrayList<>(usuarios);
    }

    public boolean usuarioExiste(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean agregarUser(String username, String pass, String fecha) throws ClassNotFoundException {

        if (!usuarioExiste(username)) {
            Usuario nuevoUsuario = new Usuario(username, pass, fecha, true, "Nuevo User", "src/imags/person1.png");
            usuarioActual = nuevoUsuario;
            usuarios.add(nuevoUsuario);
            guardarUsuarios();
            return true;

        }
        System.out.println("ya esta");
        return false;

    }

    public boolean validarCredenciales(String username, String pass) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPass().equals(pass)) {
                usuarioActual = usuario;
                return true;
            }
        }
        return false;
    }

    public boolean cerrarSesion() {
        if (usuarioActual != null) {
            usuarioActual = null;
            return true;
        }
        return false;
    }

    /////////////// ///FUNCIONES             CANCIONES
    public void agregarCancion(File archivoOrigen) {
        if (usuarioActual != null) {
            if (!archivoOrigen.exists() || !archivoOrigen.getName().endsWith(".mp3")) {
                JOptionPane.showMessageDialog(null, "El archivo no es valido o no existe.");
                return;
            }

            try {
                File carpetaMusica = new File(usuarioActual.getDirect(), "/musica");
                if (!carpetaMusica.exists()) {
                    carpetaMusica.mkdirs();
                }

                File archivoDestino = new File(carpetaMusica, archivoOrigen.getName());
                try (InputStream in = new FileInputStream(archivoOrigen); OutputStream out = new FileOutputStream(archivoDestino)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
                
               
                JOptionPane.showMessageDialog(null, "Canción " + archivoOrigen.getName() + " agregada a la carpeta de " + usuarioActual.getUsername());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al copiar la canción: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "el usuario es null");
        }
    }

   /* private void cargarCanciones() {
    if (usuarioActual != null) {
        songs.clear();
        String rutaMusica = "src/users/" + usuarioActual.getUsername() + "/musica";
        File carpeta = new File(rutaMusica);

        if (carpeta.exists() && carpeta.isDirectory()) {
            for (File archivo : carpeta.listFiles()) {
                if (archivo.isFile() && archivo.getName().endsWith(".mp3")) {
                    try {
                        AudioFile audioFile = AudioFileIO.read(archivo); 
                        Tag tag = audioFile.getTag();

                        String titulo = tag != null ? tag.getFirst(FieldKey.TITLE) : "Desconocido";
                        String artista = tag != null ? tag.getFirst(FieldKey.ARTIST) : "Desconocido";
                        String album = tag != null ? tag.getFirst(FieldKey.ALBUM) : "Desconocido";
                        int duracionSegundos = audioFile.getAudioHeader().getTrackLength();
                        int minutos = duracionSegundos / 60;
                        int segundos = duracionSegundos % 60;
                        String duracion = String.format("%02d:%02d", minutos, segundos);

                        String portada = "Sin portada"; 
                        if (tag != null && tag.getFirstArtwork() != null) {
                            Artwork artwork = tag.getFirstArtwork();
                            byte[] imagenBytes = artwork.getBinaryData();

                            File archivoPortada = new File(carpeta, archivo.getName() + "_cover.jpg");
                            try (FileOutputStream fos = new FileOutputStream(archivoPortada)) {
                                fos.write(imagenBytes);
                                portada = archivoPortada.getAbsolutePath(); 
                            }
                        }

                        Musica cancion = new Musica(titulo, artista, album, duracion, archivo.getPath(), portada);
                        songs.add(cancion);

                    } catch (Exception e) {
                        System.err.println("Error al cargar la canción: " + archivo.getName() + ", " + e.getMessage());
                    }
                }
            }
            System.out.println("Canciones cargadas correctamente.");
        } else {
            System.out.println("La carpeta de música no existe.");
        }
    } else {
        System.out.println("No hay un usuario autenticado.");
    }
}*/
    
    public void cargaInicial() {
        
        if (usuarioActual != null) {
        File songsDir = new File("src/users/" + usuarioActual.getUsername() + "/musica");
        try {
            File[] archivos = songsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
            songs.clear(); 
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
    

    
    /*private void cargarCanciones() {
        if (usuarioActual != null) {

            String rutaMusica = "src/users/" + usuarioActual.getUsername() + "/musica";
            File carpeta = new File(rutaMusica);

            if (carpeta.exists() && carpeta.isDirectory()) {
                for (File archivo : carpeta.listFiles()) {
                    if (archivo.isFile() && archivo.getName().endsWith(".mp3")) {
                        songs.add(archivo.getAbsolutePath());
                    }
                }
            } else {
                System.out.println("La carpeta " + rutaMusica + " no existe.");
            }
        }
    }*/


    public boolean verificarCancion(String nombreCancion) {
        if (usuarioActual != null) {
            if (!nombreCancion.endsWith(".mp3")) {
                nombreCancion += ".mp3";
            }

            String rutaCancion = "src/users/" + usuarioActual.getUsername() + "/musica/" + nombreCancion;

            File archivo = new File(rutaCancion);

            System.out.println("Verificando canción en ruta: " + archivo.getAbsolutePath());

            if (archivo.exists() && archivo.isFile()) {
                return true;
            }
        }
        return false;
    }

   /* public void eliminarCancion(String nombreCancion) {

        if (usuarioActual != null) {

            if (!nombreCancion.endsWith(".mp3")) {
                nombreCancion += ".mp3";
            }

            String rutaCancion = "src/users/" + usuarioActual.getUsername() + "/musica/" + nombreCancion;
            File archivo = new File(rutaCancion);

            if (archivo.exists() && archivo.isFile() && archivo.getName().endsWith(".mp3")) {
                if (archivo.delete()) {
                     JOptionPane.showMessageDialog(null, "La canción " + nombreCancion + " ha sido eliminada.");
                    songs.removeIf(cancion -> cancion.getRuta().equals(archivo.getAbsolutePath()));
                    actualizarMusica();
                    
                   
                    
                    
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar la canción.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La canción no existe o no es un archivo válido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no está autenticado.");
        }
    }*/
    
    public void eliminarCancion(String nombreCancion) {

        if (usuarioActual != null) {

            if (!nombreCancion.endsWith(".mp3")) {
                nombreCancion += ".mp3";
            }

            String rutaCancion = "src/users/" + usuarioActual.getUsername() + "/musica/" + nombreCancion;
            File archivo = new File(rutaCancion);

            if (archivo.exists() && archivo.isFile() && archivo.getName().endsWith(".mp3")) {
                if (archivo.delete()) {
                    JOptionPane.showMessageDialog(null, "La canción " + nombreCancion + " ha sido eliminada.");
                    songs.removeIf(cancion -> cancion.equals(archivo.getAbsolutePath()));
                    File archivoPortada = new File(rutaCancion + "_cover.jpg");
                        if (archivoPortada.exists() && archivoPortada.isFile()) {
                            if (archivoPortada.delete()) {
                                System.out.println("La portada asociada " + archivoPortada.getName() + " ha sido eliminada.");
                            } else {
                                System.out.println("No se pudo eliminar la portada asociada.");
                            }
                        }
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar la canción.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La canción no existe o no es un archivo válido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no está autenticado.");
        }
    }

    public void actualizarMusica() {
        if (usuarioActual != null) {
            cargaInicial();
            System.out.println("Lista de juegos actualizada.");
        } else {
            System.out.println("No se pudo actualizar la lista de juegos porque no hay un usuario autenticado.");
        }
    }

    public ArrayList<Musica> getCanciones() {
        if (usuarioActual != null) {
            actualizarMusica(); 
            return new ArrayList<>(songs); 
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no está autenticado.");
            return new ArrayList<>(); 
        }
    }
    
    

    /////////////// ///FUNCIONES             JUEGOS
    public void agregarJuego(File archivoOrigen) {
        if (usuarioActual != null) {
            if (!archivoOrigen.exists() || !archivoOrigen.getName().endsWith(".game")) {
                JOptionPane.showMessageDialog(null, "El archivo no es valido o no existe.");
                return;
            }

            try {
                File carpetaJuego = new File(usuarioActual.getDirect(), "juegos");
                if (!carpetaJuego.exists()) {
                    carpetaJuego.mkdirs();
                }

                File archivoDestino = new File(carpetaJuego, archivoOrigen.getName());

                Files.copy(archivoOrigen.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);

                JOptionPane.showMessageDialog(null, "Juego " + archivoOrigen.getName() + " agregado a la carpeta de " + usuarioActual.getUsername());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al copiar el juego: " + e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "el usuario es null");
        }
    }

    public boolean verificarJuego(String nombreJuego) {
        if (usuarioActual != null) {
            if (!nombreJuego.endsWith(".game")) {
                nombreJuego += ".game";
            }

            String rutaCancion = "src/users/" + usuarioActual.getUsername() + "/juegos/" + nombreJuego;

            File archivo = new File(rutaCancion);

            System.out.println("Verificando Juego en ruta: " + archivo.getAbsolutePath());

            if (archivo.exists() && archivo.isFile()) {
                return true;
            }
        }
        return false;
    }

    public void eliminarJuego(String nombreJuego) {

        if (usuarioActual != null) {

            if (!nombreJuego.endsWith(".game")) {
                nombreJuego += ".game";
            }

            String rutaCancion = "src/users/" + usuarioActual.getUsername() + "/juegos/" + nombreJuego;
            File archivo = new File(rutaCancion);

            if (archivo.exists() && archivo.isFile() && archivo.getName().endsWith(".game")) {
                if (archivo.delete()) {
                    JOptionPane.showMessageDialog(null, "El juego " + nombreJuego + " ha sido eliminado");
                    games.removeIf(juego -> juego.equals(archivo.getAbsolutePath()));
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el juego.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El juego no existe o no es un archivo valido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no está autenticado.");
        }
    }

    public void actualizarJuegos() {
        if (usuarioActual != null) {
            cargarJuegos(); 
            System.out.println("Lista de juegos actualizada.");
        } else {
            System.out.println("No se pudo actualizar la lista de juegos porque no hay un usuario autenticado.");
        }
    }

    private void cargarJuegos() {
        if (usuarioActual != null) {

            String rutaJuego = "src/users/" + usuarioActual.getUsername() + "/juegos";
            File carpeta = new File(rutaJuego);

            if (carpeta.exists() && carpeta.isDirectory()) {
                File[] archivos = carpeta.listFiles();
                games.clear(); 

                if (archivos == null || archivos.length == 0) {
                    System.out.println("No hay archivos en la carpeta de juegos.");
                    return;
                }

                for (File archivo : archivos) {
                    if (archivo.isFile() && archivo.getName().endsWith(".game")) {
                        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
                            String nombre = raf.readUTF();
                            String genero = raf.readUTF();
                            String desarrollador = raf.readUTF();
                            String lanzamiento = raf.readUTF();
                            String ruta = raf.readUTF();
                            String foto = raf.readUTF();

                            String ruta2 = archivo.getPath();

                            Juego juego = new Juego(nombre, genero, desarrollador, lanzamiento, ruta2, foto);
                            games.add(juego);
                        } catch (IOException e) {
                            System.err.println("Error al cargar el juego desde el archivo: " + archivo.getName());
                        }
                    }
                }
            } else {
                System.out.println("La carpeta " + rutaJuego + " no existe.");
            }
        } else {
            System.out.println("El usuario es null");
        }

    }

    public ArrayList<Juego> getJuegos() {
        if (usuarioActual != null) {
            actualizarJuegos(); 
            return new ArrayList<>(games); 
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no está autenticado.");
            return new ArrayList<>(); 
        }
    }

}
