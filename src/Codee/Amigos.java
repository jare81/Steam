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
        
        
      

    }

    private String generarNombreArchivo(String usuario1, String usuario2) {
        String[] usuarios = {usuario1, usuario2};
        Arrays.sort(usuarios);
        return usuarios[0] + "_" + usuarios[1] + "_conversacion.msg";
    }

}

