/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chat;


import Codee.Usuario;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * Clase de chat con interfaz gráfica simple
 */
public class OtroChat extends JFrame implements Observer {
    private static OtroChat servidorView;
    private Usuario actual, destino;
    private GestorConversaciones gestor;

    private JTextArea txtHistorial;
    private JTextField txtMensaje;
    private JButton btnEnviar;

    
    public OtroChat (Usuario actual, Usuario destino) {
        this.actual = actual;
        this.destino = destino;

        initComponents();

        String nombreArchivo = GestorConversaciones.obtenerArchivoConversacion(actual.getUsername(), destino.getUsername());
        gestor = new GestorConversaciones(nombreArchivo);

        cargarConversacionPrevia();

        txtHistorial.append("....BIENVENIDO....\n\n");
    }

    
    private void initComponents() {
        txtHistorial = new JTextArea(20, 50);
        txtHistorial.setEditable(false);  
        JScrollPane scrollPane = new JScrollPane(txtHistorial);

        txtMensaje = new JTextField(40);

        btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(e -> enviarMensaje());

        setTitle("Chat con " + destino.getUsername());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(scrollPane);
        add(txtMensaje);
        add(btnEnviar);
    }
    
    public void notificarConexion(String msj) {
        this.txtHistorial.append(msj + "\n");
        guardarMensaje(new Mensaje(destino.getUsername(), actual.getUsername(), msj));
    }
    
    private void guardarMensaje(Mensaje mensaje) {
        gestor.guardarMensaje(mensaje);
    }
    
    public void setUsuarios(Usuario actual, Usuario destino) {
        this.actual = actual;
        this.destino = destino;

        String nombreArchivo = GestorConversaciones.obtenerArchivoConversacion(actual.getUsername(), destino.getUsername());
        this.gestor = new GestorConversaciones(nombreArchivo);


    }

   
    private void enviarMensaje() {
        String mensaje = txtMensaje.getText();
        if (!mensaje.isEmpty()) {
            txtHistorial.append(actual.getUsername() + ": " + mensaje + "\n");

            guardarMensaje(new Mensaje(actual.getUsername(), destino.getUsername(), mensaje));

            txtMensaje.setText("");
        }
    }

    
   
    
    private void cargarConversacionPrevia() {
        List<Mensaje> mensajes = gestor.cargarConversacion();
        for (Mensaje mensaje : mensajes) {
            txtHistorial.append(mensaje + "\n");
        }
    }

   
   public static OtroChat getInstance(Usuario actual, Usuario destino) {
        if (servidorView == null) {
            servidorView = new OtroChat(actual, destino);
        }
        return servidorView;
    }
    
      public static void cerrar() {
        if (servidorView != null) {
            servidorView.dispose();
            servidorView = null;
        }
    }
      
    public void update(Observable o, Object arg) {
        String mensaje = (String) arg;
        txtHistorial.append(mensaje + "\n");
        guardarMensaje(new Mensaje(destino.getUsername(), actual.getUsername(), mensaje));
    }

    
}

