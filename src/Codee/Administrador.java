/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

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
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class Administrador {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Musica> musica;
    

    private Usuario usuarioActual;
    private Usuario us;
    private File listaUsers;
    private String nombre;
    private File bMusical;
    private File bJuegos;
    private ArrayList<String> songs;

    public Administrador() {
        usuarios = new ArrayList<>();
        songs = new ArrayList<>();
        
        usuarioActual = null;

        File directorio = new File("src/users/");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        cargarUsuarios();
        //  cargarDesdeArchivo();
        cargarCanciones();

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
                JOptionPane.showMessageDialog(null, "El archivo no es válido o no existe.");
                return;
            }

            try {
                File carpetaMusica = new File(usuarioActual.getDirect(), "musica");
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

    /* private void cargarDesdeArchivo() {
        if(usuarioActual!=null){
          
        String archivoRuta = "src/users/" + usuarioActual.getUsername() + "/canciones.txt";
        File archivo = new File(archivoRuta);

        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    songs.add(linea);
                }
            } catch (IOException e) {
                System.err.println("Error al cargar la lista de canciones: " + e.getMessage());
            }
        }
    }
    
}*/
    private void cargarCanciones() {
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
    }

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

    public ArrayList<String> getCanciones() {
        return songs;
    }

}
