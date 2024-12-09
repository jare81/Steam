package Chat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorConversaciones {
    private File archivo;

    public GestorConversaciones(String rutaArchivo) {
        this.archivo = new File(rutaArchivo);
    }
    
    public static String obtenerArchivoConversacion(String usuario1, String usuario2) {
    // Ordenar los nombres alfabéticamente
    String usuarioA = usuario1.compareTo(usuario2) < 0 ? usuario1 : usuario2;
    String usuarioB = usuario1.compareTo(usuario2) < 0 ? usuario2 : usuario1;

    String nombreArchivo = "Chat/" + usuarioA + "_" + usuarioB + ".bin";

    return nombreArchivo; // Siempre genera el mismo archivo para ambos usuarios
}


    public void guardarMensaje(Mensaje mensaje) {
        List<Mensaje> mensajes = cargarConversacion();
        mensajes.add(mensaje);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(mensajes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Mensaje> cargarConversacion() {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Mensaje>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

