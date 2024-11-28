/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
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

    private Administrador admin;

    public Usuario(String username, String pass, String fecha, boolean activo
    /*File bMusical, File bJuegos, File bChat*/) {

        this.username = username;
        this.pass = pass;
        this.fecha = fecha;
        this.activo = activo;
        descripcion = "";
        foto = "src/imags/person1.png";

        this.amixes = new ArrayList<>();
        this.solicitudesAmistad = new ArrayList<>();


        /* File carpetaUsuario = new File("src/users/" + username );
        if (!carpetaUsuario.exists()) {
            carpetaUsuario.mkdirs();
        }*/
        directorioP = new File("src/users/" + username);
        carpetaUsuario();
        iniciarArchivo();
        cargarAmigos();

        //iniciarRandom();
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

            String solicitudes = archivo.readUTF();
            if (!solicitudes.isEmpty()) {
                this.solicitudesAmistad = new ArrayList<>(Arrays.asList(solicitudes.split(",")));
            } else {
                this.solicitudesAmistad = new ArrayList<>();
            }

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
            archivo.writeUTF(String.join(",", solicitudesAmistad));
        } catch (IOException e) {
            System.out.println("Error al actualizar datos " + e.getMessage());
        }
    }

    public void agregarAmigo(String nombreAmigo) {
       /* File archivoAmigos = new File(directorioP, "amigos" + nombreAmigo + ".amix");

        if (!amixes.contains(nombreAmigo)) {
            amixes.add(nombreAmigo);
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivoAmigos))) {
                for (String amigo : amixes) {
                    dos.writeUTF(amigo);
                }
            } catch (IOException e) {
                System.out.println("Error al guardar amigos: " + e.getMessage());
            }
            System.out.println("Amigo agregado: " + nombreAmigo);
        } else {
            System.out.println("El amigo ya está en la lista.");
        }

        actualizarArchivo();
        System.out.println("Amigo agregado: " + nombreAmigo);*/
       if (!amixes.contains(nombreAmigo)) {
        amixes.add(nombreAmigo);
        guardarAmigos();

        // Agregar el usuario actual como amigo en el archivo de amigos del otro usuario
        Usuario amigo = obtenerUsuarioPorNombre(nombreAmigo);
        if (amigo != null) {
            amigo.amixes.add(this.username);
            amigo.guardarAmigos();
        }
        System.out.println("Amigo agregado: " + nombreAmigo);
    } else {
        System.out.println("El amigo ya está en la lista.");
    }
       
    }

    public void eliminarAmigo(String nombreAmigo) {
        /*if (amixes.remove(nombreAmigo)) {
            actualizarArchivo();
            File archivoAmigos = new File(directorioP, "amigos/" + nombreAmigo + ".amix");
            if (archivoAmigos.exists()) {
                archivoAmigos.delete();
            }
            System.out.println("Amigo eliminado: " + nombreAmigo);
        } else {
            System.out.println("El amigo no está en la lista.");
        }*/
        if (amixes.remove(nombreAmigo)) {
        guardarAmigos();
        
        // Remover el usuario actual de la lista de amigos del otro usuario
        Usuario amigo = obtenerUsuarioPorNombre(nombreAmigo);
        if (amigo != null && amigo.amixes.remove(this.username)) {
            amigo.guardarAmigos();
        }
        System.out.println("Amigo eliminado: " + nombreAmigo);
    } else {
        System.out.println("El amigo no está en la lista.");
    }
        
        
    }

    private void cargarAmigos() {
        File archivoAmigos = new File(carpetaAmigos, "amigos.amix");
        if (archivoAmigos.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoAmigos))) {
                amixes = (ArrayList<String>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar amigos: " + e.getMessage());
            }
        }
    }

    private void guardarAmigos() {
        File archivoAmigos = new File(carpetaAmigos, "amigos.amix");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoAmigos))) {
            oos.writeObject(amixes);
        } catch (IOException e) {
            System.out.println("Error al guardar amigos: " + e.getMessage());
        }
    }

    public ArrayList<String> getAmigos() {
        return new ArrayList<>(amixes);
    }

    public boolean isAmigo(Usuario usuario) {
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
        receptor.solicitudesAmistad.add(this.username);
        receptor.guardarSolicitudes();
        JOptionPane.showMessageDialog(null, "Solicitud enviada a: " + receptor.getUsername());
    }

    public void aceptarSolicitud(String amigoNombre) {
        if (solicitudesAmistad.contains(amigoNombre)) {
            // Agregar el amigo a ambas listas
            amixes.add(amigoNombre);
            Usuario amigo = obtenerUsuarioPorNombre(amigoNombre);
            if (amigo != null) {
                amigo.amixes.add(this.username);
                amigo.guardarAmigos();
            }

            solicitudesAmistad.remove(amigoNombre);
            guardarAmigos();
            guardarSolicitudes();

            JOptionPane.showMessageDialog(null, "Ahora eres amigo de: " + amigoNombre);
        } else {
            JOptionPane.showMessageDialog(null, "No hay una solicitud pendiente de: " + amigoNombre);
        }

    }

    public void rechazarSolicitud(String nombreUsuario) {
        if (solicitudesAmistad.remove(nombreUsuario)) {
            guardarSolicitudes();
            System.out.println("Solicitud rechazada de: " + nombreUsuario);
        } else {
            System.out.println("No hay una solicitud pendiente de: " + nombreUsuario);
        }
    }

    private void guardarSolicitudes() {
        File archivoSolicitudes = new File(directorioP, "solicitudes.bin");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoSolicitudes))) {
            oos.writeObject(solicitudesAmistad);
        } catch (IOException e) {
            System.out.println("Error al guardar solicitudes: " + e.getMessage());
        }
    }

    public void actualizarListaAmigos(DefaultListModel<String> modeloListaAmigos) {
        modeloListaAmigos.clear();
        for (String amigo : amixes) { 
            modeloListaAmigos.addElement(amigo); 
        }
    }

    public ArrayList<String> getSolicitudesPendientes() {
        return new ArrayList<>(solicitudesAmistad); // Devuelve una copia para evitar modificaciones directas
    }

    private Usuario obtenerUsuarioPorNombre(String nombre) {
        File carpetaUsuario = new File("src/users/" + nombre);
        if (carpetaUsuario.exists()) {
            return new Usuario(nombre, "", "", true);
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
