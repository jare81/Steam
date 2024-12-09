/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensaje implements Serializable {
    private String emisor;
    private String receptor;
    private String contenido;
    private LocalDateTime fechaHora;

    public Mensaje(String emisor, String receptor, String contenido) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.contenido = contenido;
        this.fechaHora = LocalDateTime.now();
    }

    public String getEmisor() {
        return emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public String getContenido() {
        return contenido;
    }

    public String getFechaHora() {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
         String fechaFormateada = fechaHora.format(formateador);
        return fechaFormateada;
    }

    @Override
    public String toString() {
        return getFechaHora() + " "  + ": \n  " + contenido;
    }
}
