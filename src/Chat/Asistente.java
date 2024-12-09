package Chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Asistente implements Runnable {

    /**
     * atributos de la clase asistente
     */
    private Socket sc;
    private DataInputStream in;
    private boolean centinela;

    /**
     * contructor de la clase
     * 
     * @param sc
     * @param server 
     */
    public Asistente(Socket sc, Servidor server) {
        this.sc = sc;
        Servidor.instancia = server;
        this.centinela = true;
    }

    /**
     * metodo run
     */
    @Override
    public void run() {

        try {
            String mensaje;
            in = new DataInputStream(sc.getInputStream());

            while (centinela == true) {

                
                //leo el mensaje del cliente
                mensaje = in.readUTF();

                if (mensaje.equalsIgnoreCase("cerrar")) {

                    centinela = false;

                    //Servidor.instancia.desconectarCliente("UN CLIENTE SE DESCONECTO");
                    in.close();
                    sc.close();

                } else {
                    //envio el mensaje al servidor
                    Servidor.instancia.notificacion(mensaje);
                }

            }


        } catch (IOException ex) {
            Logger.getLogger(Asistente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
