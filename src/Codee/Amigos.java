/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dell
 */
public class Amigos {

    private String nombre;
    private File carpetaChats;
    private File archivoConversacion;

    public Amigos(String nombre, File directorioUsuario) {
        this.nombre = nombre;

        carpetaChats = new File(directorioUsuario, "chats");
        if (!carpetaChats.exists()) {
            carpetaChats.mkdirs();
        }

    }

    public String getNombre() {
        return nombre;
    }
    
    public File getDirect() {
    return carpetaChats;
}

    /*public void guardarMensaje(String mensaje) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivoConversacion, true))) {
            dos.writeUTF(mensaje);
        } catch (IOException e) {
            System.out.println("Error al guardar mensaje en conversación binaria: " + e.getMessage());
        }
    }*/
    public void guardarMensajeCompartido(String mensaje, File directorioUsuarioActual) {
        String nombreArchivo = generarNombreArchivo(directorioUsuarioActual.getName(), nombre);
        File archivoCompartido = new File(directorioUsuarioActual.getParent(), nombreArchivo);

        String mensajeCompleto = directorioUsuarioActual.getName() + ": " + mensaje;

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivoCompartido, true))) {
            dos.writeUTF(mensajeCompleto);
        } catch (IOException e) {
            System.out.println("Error al guardar mensaje en conversación compartida: " + e.getMessage());
        }
        
        
        
        /*String nombreArchivo = generarNombreArchivo(directorioUsuarioActual.getName(), nombre);

    // Archivo compartido en el directorio del emisor
    File archivoCompartidoEmisor = new File(new File(directorioUsuarioActual, "chats"), nombreArchivo);

    // Archivo compartido en el directorio del receptor
    File archivoCompartidoReceptor = new File(new File(directorioUsuarioAmigo, "chats"), nombreArchivo);

    // Mensaje completo con el nombre del emisor
    String mensajeCompleto = directorioUsuarioActual.getName() + ": " + mensaje;

    // Guardar mensaje en el archivo del emisor
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivoCompartidoEmisor, true))) {
        dos.writeUTF(mensajeCompleto);
    } catch (IOException e) {
        System.out.println("Error al guardar mensaje en conversación compartida del emisor: " + e.getMessage());
    }

    // Guardar mensaje en el archivo del receptor
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivoCompartidoReceptor, true))) {
        dos.writeUTF(mensajeCompleto);
    } catch (IOException e) {
        System.out.println("Error al guardar mensaje en conversación compartida del receptor: " + e.getMessage());
    }*/
    }

    public ArrayList<String> cargarConversacionCompartida(String nombreAmigo, File directorioUsuarioActual) {
        String nombreArchivo = generarNombreArchivo(directorioUsuarioActual.getName(), nombreAmigo);
        File archivoCompartido = new File(directorioUsuarioActual.getParent(), nombreArchivo);

        ArrayList<String> mensajes = new ArrayList<>();
        if (!archivoCompartido.exists()) {
            return mensajes;
        }
        try (DataInputStream dis = new DataInputStream(new FileInputStream(archivoCompartido))) {
            while (dis.available() > 0) {
                mensajes.add(dis.readUTF());
            }
        } catch (IOException e) {
            System.out.println("Error al cargar conversación binaria compartida: " + e.getMessage());
        }
        return mensajes;
        
        
        /*File carpetaChatsUsuario = new File(directorioUsuarioActual, "chats");
        if (!carpetaChatsUsuario.exists()) {
            return new ArrayList<>();
        }

        String nombreArchivo = generarNombreArchivo(directorioUsuarioActual.getName(), nombreAmigo);
        File archivoCompartido = new File(carpetaChatsUsuario, nombreArchivo);

        ArrayList<String> mensajes = new ArrayList<>();
        if (!archivoCompartido.exists()) {
            return mensajes;
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(archivoCompartido))) {
            while (dis.available() > 0) {
                mensajes.add(dis.readUTF());
            }
        } catch (IOException e) {
            System.out.println("Error al cargar conversación binaria compartida: " + e.getMessage());
        }
        return mensajes;*/

    }

    private String generarNombreArchivo(String usuario1, String usuario2) {
        String[] usuarios = {usuario1, usuario2};
        Arrays.sort(usuarios);
        return usuarios[0] + "_" + usuarios[1] + "_conversacion.msg";
    }

}

/*public ArrayList<String> cargarConversacion() {
        ArrayList<String> mensajes = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(archivoConversacion))) {
            while (dis.available() > 0) {
                mensajes.add(dis.readUTF());
            }
        } catch (IOException e) {
            System.out.println("Error al cargar conversación binaria: " + e.getMessage());
        }
        return mensajes;
    }
}*/
