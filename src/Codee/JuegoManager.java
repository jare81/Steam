/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dell
 */
public class JuegoManager {
    private File gameDir;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private ArrayList<Juego> games;
    
    
    public JuegoManager(){
        gameDir = new File("src/Videojuegos");
        if(!gameDir.exists()){
            gameDir.mkdir();
        }
        games = new ArrayList<>();
        cargaInicial();
    }
    
    public ArrayList<Juego> getArray(){
        return games;
    }
    
    public void agregarJuego(String nombre, String genero, String desarrollador, String lanzamiento, String ruta, String foto) {
        try {
            //Date fechaLanzamiento = dateFormat.parse(lanzamiento);

            File gameFile = new File(gameDir, nombre + ".game");
            String rutaA = gameFile.getPath();
            
            Juego nuevoJuego = new Juego(nombre, genero, desarrollador, lanzamiento, rutaA, foto);
            
            // Guardar en archivo binario
           
            try (RandomAccessFile raf = new RandomAccessFile(gameFile, "rw")) {
                raf.writeUTF(nuevoJuego.getNombre());
                raf.writeUTF(nuevoJuego.getGenero());
                raf.writeUTF(nuevoJuego.getDesarrollador());
                raf.writeUTF(nuevoJuego.getLanzamiento());
                raf.writeUTF(nuevoJuego.getRuta());
                raf.writeUTF(nuevoJuego.getFoto());
            }

            // Agregar a la lista
            games.add(nuevoJuego);
            System.out.println("Juego agregado a la lista: " + nuevoJuego.getNombre());
            System.out.println("Total de juegos en la lista: " + games.size());
            //System.out.println("Juego agregado exitosamente.");
        
        } catch (IOException e) {
            System.err.println("Error al guardar el juego: " + e.getMessage());
        }
    }
    
    /* public void cargaInicial() {
        games.add(new Juego("Fortnite", "Battle Royale", "Epic Games", "01/Noviembre", "ruta", "src/Videojuegos/fortnite.jpg"));
        games.add(new Juego("Among Us", "Aventura", "Mojang Studios", "01/Noviembre", "ruta", "src/Videojuegos/amongus.jpg"));
        games.add(new Juego("Valorant", "Hero Shooter", "Riot Games", "01/Noviembre", "ruta", "src/Videojuegos/valorant.png"));
        games.add(new Juego("Call of Duty", "Battle Royale", "Infinity Ward", "01/Noviembre", "ruta", "src/Videojuegos/cod.jpg"));
        games.add(new Juego("Grand Theft Auto V", "Acción y Aventura", "Rockstar North", "01/Noviembre", "ruta", "src/Videojuegos/gta5.jpg"));
        games.add(new Juego("Little Nightmares", "Terror", "Dennis Talajic", "01/Noviembre", "ruta", "src/Videojuegos/little.jpg"));
        games.add(new Juego("Minecraft", "Aventura", "Mojang Studios", "01/Noviembre", "ruta", "src/Videojuegos/minecraft.jpg"));
        games.add(new Juego("The Quarry", "Terror y Drama", "Supermassive Games", "01/Noviembre", "ruta", "src/Videojuegos/theQuarry.jpg"));
        games.add(new Juego("Fall Guys", "Party Royale", "Joseph Walsh", "01/Noviembre", "ruta", "src/Videojuegos/fallguys.jpg"));
        games.add(new Juego("Final Fantasy", "Fantasía y Ciencia Ficción", "Square Enix", "01/Noviembre", "ruta", "src/Videojuegos/final.jpg"));
        games.add(new Juego("Genshin Impact", "Rol de Acción", "HoYoverse", "01/Noviembre", "ruta", "src/Videojuegos/genshin.jpg"));
    
    }*/
    
    
    
    private void cargaInicial() {
        if (gameDir.exists() && gameDir.isDirectory()) {
            File[] files = gameDir.listFiles((dir, name) -> name.endsWith(".game"));
            if (files != null) {
                for (File file : files) {
                    try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                        String nombre = raf.readUTF();
                        String genero = raf.readUTF();
                        String desarrollador = raf.readUTF();
                        String lanzamiento = raf.readUTF();
                        String ruta = raf.readUTF();
                        String foto = raf.readUTF();

                        games.add(new Juego(nombre, genero, desarrollador, lanzamiento, ruta, foto));
                    } catch (IOException e) {
                        System.err.println("Error al cargar el archivo: " + file.getName());
                    }
                }
            }
        }
    }
    
    
    
    
    
    
    
    
}
