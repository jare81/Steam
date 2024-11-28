/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Reproductor{
 private Player player;
 private String rutaArchivo;




    public Reproductor(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void reproducir() {
        try {
            FileInputStream archivo = new FileInputStream(rutaArchivo);
            player = new Player(archivo);
            new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    System.out.println("Error al reproducir: " + e.getMessage());
                }
            }).start();
        } catch (FileNotFoundException | JavaLayerException e) {
            System.out.println("No se pudo cargar el archivo: " + e.getMessage());
        }
    }

    public void detener() {
        if (player != null) {
            player.close();
        }
    }
    

}