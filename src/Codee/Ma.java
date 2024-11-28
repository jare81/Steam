/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

/**
 *
 * @author Dell
 */
public class Ma {
    public static void main(String[] args) {
        Musica cancion = new Musica("Titulo", "Artista", "Album", 2742, "./src/apt.mp3");
        Reproductor reproductor = new Reproductor (cancion.getRuta());
        reproductor.reproducir();
    }
}
