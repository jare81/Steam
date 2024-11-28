/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Codee;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class Administrador  {

    private ArrayList<Usuario> usuarios;
    private Usuario usuarioActual;
    private Usuario us;
     private File listaUsers;
    private String nombre;
    private File bMusical;
    private File bJuegos;
    
    
    public Administrador() {
        usuarios = new ArrayList<>();
        usuarioActual = null;

        File directorio = new File("src/users/");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        
        cargarUsuarios();
      
        
        
    }

     
    public void cargarUsuarios() {
      // usuarios.clear();
       
       File directorio = new File("src/users/");
       File[] carpetas = directorio.listFiles();
       
       if(carpetas!=null){
           for(File carpeta : carpetas){
               if(carpeta.isDirectory()){
                   String nombreUsuario = carpeta.getName();
                   File archivoUsuario = new File(carpeta, nombreUsuario + ".user");
                   
                    if (archivoUsuario.exists()) {
                        try (RandomAccessFile archivo = new RandomAccessFile(archivoUsuario, "r")) {
                            String username = archivo.readUTF();
                            String pass = archivo.readUTF();
                            String fecha = archivo.readUTF();
                            boolean activo = archivo.readBoolean();
                            String descripcion = archivo.readUTF();

                            usuarios.add(new Usuario(username, pass, fecha, activo));
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
            File archivoUsuario = new File("src/users/"+ us.getUsername() + "/" + username + ".user");
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

    public boolean agregarUser(String username, String pass, String fecha /*, File jj, File kk, File pp*/) throws ClassNotFoundException {

        if (!usuarioExiste(username)) {
            Usuario nuevoUsuario = new Usuario(username, pass, fecha, true /*, jj, kk, pp*/);
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

}
