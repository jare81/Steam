/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class Usuario {

    private String username;
    private String pass;
    private String fecha;
    private boolean activo;
    private String descripcion;
    private String foto;

    private RandomAccessFile archivo;
    private File directorioP;
    private File carpetaChats;
    private File carpetaJuegos;
    private File carpetaMusica;
    private File carpetaAmigos;

    private ArrayList<String> amixes;
    private ArrayList<String> solicitudesAmistad;

    private File song;

    private Administrador admin;

    public Usuario(String username, String pass, String fecha, boolean activo, String descripcion, String foto) {

        this.username = username;
        this.pass = pass;
        this.fecha = fecha;
        this.activo = activo;
        this.descripcion = descripcion.isEmpty() ? "" : descripcion;
        this.foto = foto.isEmpty() ? "" : foto;

        amixes = new ArrayList<>();
        solicitudesAmistad = new ArrayList<>();

        directorioP = new File("src/users/" + username);
        carpetaUsuario();
        iniciarArchivo();
        cargarAmigos();

    }

    public File getDirect() {
        return directorioP;
    }

    private void carpetaUsuario() {
        if (!directorioP.exists()) {
            directorioP.mkdirs();
        }

        carpetaChats = new File(directorioP, "chats");
        if (!carpetaChats.exists()) {
            carpetaChats.mkdirs();
        }

        carpetaJuegos = new File(directorioP, "juegos");
        if (!carpetaJuegos.exists()) {
            carpetaJuegos.mkdirs();
        }

        carpetaMusica = new File(directorioP, "musica");
        if (!carpetaMusica.exists()) {
            carpetaMusica.mkdirs();
        }

        carpetaAmigos = new File(directorioP, "amigos");
        if (!carpetaAmigos.exists()) {
            carpetaAmigos.mkdirs();
        }

    }

    private void iniciarArchivo() {
        try {
            File archivoUsuario = new File(directorioP, username + ".user");
            archivo = new RandomAccessFile(archivoUsuario, "rw");

            if (archivo.length() == 0) {
                archivo.writeUTF(username);
                archivo.writeUTF(pass);
                archivo.writeUTF(fecha);
                archivo.writeBoolean(activo);
                archivo.writeUTF(descripcion);
                archivo.writeUTF(foto);
                archivo.writeUTF("");

            }

        } catch (IOException e) {
            System.out.println("Error al inicializar archivo para usuario " + username + ": " + e.getMessage());

        }
    }

    public void cargarArchivo() throws ClassNotFoundException {
        try {
            archivo.seek(0);
            this.username = archivo.readUTF();
            this.pass = archivo.readUTF();
            this.fecha = archivo.readUTF();
            this.activo = archivo.readBoolean();
            this.descripcion = archivo.readUTF();
            this.foto = archivo.readUTF();

            System.out.println("Usuario cargado: " + username + ", Contraseña: " + pass + ", Fecha: " + fecha + ", Activo: " + activo);
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo " + e.getMessage());
        }
    }

    public void actualizarArchivo() {
        try {
            archivo.seek(0);
            archivo.writeUTF(username);
            archivo.writeUTF(pass);
            archivo.writeUTF(fecha);
            archivo.writeBoolean(activo);
            archivo.writeUTF(descripcion);
            archivo.writeUTF(foto);
        } catch (IOException e) {
            System.out.println("Error al actualizar datos " + e.getMessage());
        }
    }

    
    ////////////////////////////////////FUNCIONES                  AMIGOS Y SOLICITUDES
    public void agregarAmigo(String nombreAmigo) {
          cargarAmigos();
        if (!amixes.contains(nombreAmigo)) {
            amixes.add(nombreAmigo);
            guardarAmigos();

            // Obtener el amigo
            Usuario amigo = obtenerUsuarioPorNombre(nombreAmigo);
            if (amigo != null) {
                amigo.amixes.add(username);
                amigo.guardarAmigos();  
            }
            JOptionPane.showMessageDialog(null,"Amigo agregado: " + nombreAmigo);
        } else {
            JOptionPane.showMessageDialog(null,"El amigo ya está en la lista.");
        }
    }

    public void eliminarAmigo(String nombreAmigo) {
        cargarAmigos();
        if (amixes.remove(nombreAmigo)) {
            guardarAmigos();
            

            Usuario amigo = obtenerUsuarioPorNombre(nombreAmigo);
            
                if (amigo != null) {
                    amigo.amixes.remove(username);
                    amigo.guardarAmigos();  
                 }
               
            JOptionPane.showMessageDialog(null, "Amigo eliminado: " + nombreAmigo);
        } else {
            System.out.println("El amigo no está en la lista.");
        }
    }

    public void cargarAmigos() {
        File archivoAmigos = new File(directorioP + "/amigos/amigos.amix");
        if (archivoAmigos.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoAmigos))) {
                amixes = (ArrayList<String>) ois.readObject();
                System.out.println("Lista de amigos cargada desde archivo binario.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar la lista de amigos: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró archivo binario de amigos.");
        }
    }

    private void guardarAmigos() {
        File archivoAmigos = new File(directorioP + "/amigos/amigos.amix");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoAmigos))) {
            oos.writeObject(amixes);
            System.out.println("Lista de amigos guardada en formato binario.");
        } catch (IOException e) {
            System.out.println("Error al guardar la lista de amigos: " + e.getMessage());
        }
    }

    public ArrayList<String> getAmigos() {
        cargarAmigos();
        return new ArrayList<>(amixes);
    }

    public boolean isAmigo(Usuario usuario) {
         cargarAmigos();
        return amixes.contains(usuario.getUsername());
    }

    public void enviarSolicitud(Usuario receptor) {
        if (receptor == null || this.equals(receptor)) {
            JOptionPane.showMessageDialog(null, "No puedes enviarte una solicitud a ti mismo.");
            return;
        }
        if (receptor.solicitudesAmistad.contains(this.username)) {
            JOptionPane.showMessageDialog(null, "Ya enviaste una solicitud a este usuario.");
            return;
        }
        if (receptor.isAmigo(this)) {
            JOptionPane.showMessageDialog(null, "Ya son amigos.");
            return;
        }

        // receptor
        receptor.solicitudesAmistad.add(this.username);
        receptor.guardarSolicitudes();

        //remitente
        this.guardarSolicitudes();

        JOptionPane.showMessageDialog(null, "Solicitud enviada a: " + receptor.getUsername());
    }

    public void aceptarSolicitud(String amigoNombre) {
        if (!solicitudesAmistad.contains(amigoNombre)) {
            JOptionPane.showMessageDialog(null, "No hay una solicitud pendiente de: " + amigoNombre);
            return;
        }

        Usuario remitente = obtenerUsuarioPorNombre(amigoNombre);
        if (remitente == null) {
            JOptionPane.showMessageDialog(null, "El usuario no existe.");
            return;
        }

        this.agregarAmigo(amigoNombre);
        remitente.agregarAmigo(this.username);

        solicitudesAmistad.remove(amigoNombre);
        guardarSolicitudes();
        
        this.guardarAmigos(); 
        remitente.guardarAmigos();
        JOptionPane.showMessageDialog(null, "Ahora eres amigo de: " + amigoNombre);
    }

    public void rechazarSolicitud(String nombreUsuario) {
        if (solicitudesAmistad.remove(nombreUsuario)) {
            guardarSolicitudes();
            JOptionPane.showMessageDialog(null, "Solicitud rechazada para: " + nombreUsuario);
        } else {
            JOptionPane.showMessageDialog(null, "No hay una solicitud pendiente de: " + nombreUsuario);
        }
    }

    private void guardarSolicitudes() {
        File archivoSolicitudes = new File(directorioP, "solicitudes.sol");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoSolicitudes))) {
            oos.writeObject(solicitudesAmistad);
        } catch (IOException e) {
            System.out.println("Error al guardar solicitudes: " + e.getMessage());
        }
    }

    

    public ArrayList<String> getSolicitudesPendientes() {
        return new ArrayList<>(solicitudesAmistad);
    }

    private Usuario obtenerUsuarioPorNombre(String nombre) {
        File carpetaUsuario = new File("src/users/" + nombre);
        if (carpetaUsuario.exists()) {

            Usuario usuario = new Usuario(nombre, "", "", true, "", "");
            File archivoAmigos = new File(carpetaUsuario.getPath() + "/amigos/amigos.amix");
            if (archivoAmigos.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoAmigos))) {
                    usuario.amixes = (ArrayList<String>) ois.readObject();
                    System.out.println("Lista de amigos cargada para: " + nombre);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error al cargar la lista de amigos para " + nombre + ": " + e.getMessage());
                }
            }
            return usuario;
        }
        return null;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        actualizarArchivo();
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public String getFecha() {
        return fecha;
    }

    public void setPass(String pass) {
        this.pass = pass;
        actualizarArchivo();
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
        actualizarArchivo();
    }

    public boolean getActivo() {
        return activo;
    }

    public void setFoto(String ff) {
        this.foto = ff;
        actualizarArchivo();
    }

    public String getFoto() {
        return foto;

    }

    public void cerrarArchivo() {
        try {
            if (archivo != null) {
                archivo.close();
            }
        } catch (IOException e) {
            System.out.println("Error al cerrar archivo: " + e.getMessage());
        }
    }

}
