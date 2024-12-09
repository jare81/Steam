package Chat;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Observable implements Runnable {

    /**
     * atributos de a clase servidor
     */
    
    private ServerSocket server;
    private Socket sc;
    private int puerto;
    private OtroChat vistaServidor;
    public static Servidor instancia;

    //constructor de la clase
    public Servidor(int puerto) {

        this.puerto = puerto;
        
    }
   
    public void setVistaServidor(OtroChat vistaServidor){
        this.vistaServidor = vistaServidor;
    }

    @Override
    public void run() {
        System.out.println("se inicio el servidor");
        try {

            server = new ServerSocket(puerto);
        System.out.println("Servidor iniciado en el puerto " + puerto);

        while (true) {
            sc = server.accept();
           // vistaServidor.notificarConexion("NUEVO CLIENTE CONECTADO");
            Asistente asistente = new Asistente(sc, this);
            Thread t = new Thread(asistente);
            t.start();
        }
    } catch (BindException e) {
        System.out.println("Error: El puerto ya está en uso. El servidor no puede iniciar.");
    } catch (IOException ex) {
        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }

    /**
     * metodo con el cual notificamos a los observadores
     * @param mensaje 
     */
    public void notificacion(String mensaje) {

        //notifico los cambios a los observadores
        this.setChanged();
        this.notifyObservers(mensaje + "\n");
        this.clearChanged();
    }

    /**
     * metodo para informar que un cliente se desconecto
     * @param mensaje 
     */
    public void desconectarCliente(String mensaje) {
        vistaServidor.notificarConexion(mensaje);
    }
    
     public static Servidor getInstance(int puerto) {
        if (instancia == null) {
            instancia = new Servidor(puerto);
        }
        return instancia;
    }

    public static void detener() throws IOException {
        if (instancia != null ) {
            instancia.detener();
            instancia = null;
        }
    }

}
