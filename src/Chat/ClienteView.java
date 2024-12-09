/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ClienteView extends JFrame implements Observer {

    private String nombre;
    private Cliente cliente;
    private String mensaje;

    // Components
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JButton btnEmpezar;
    private JTextField txtMensaje;
    private JButton btnEnviar;
    private JButton btnSalir;
    private JTextArea txtConversacion;
    private JScrollPane scrollPane;

    public ClienteView(String user) {
        initComponents();
        
        nombre = user;
        txtNombre.setText("CHAT DESDE " + nombre.toUpperCase());

        cliente = new Cliente(6000);

        txtMensaje.setEnabled(true);
        btnEnviar.setEnabled(true);
        btnSalir.setEnabled(true);
        txtNombre.setEnabled(false);
        btnEmpezar.setEnabled(false);
        
        setAlwaysOnTop(true);
    }

    private void initComponents() {
        lblNombre = new JLabel("Nombre de usuario");
        txtNombre = new JTextField(15);
        btnEmpezar = new JButton("Empezar");
        txtMensaje = new JTextField(20);
        btnEnviar = new JButton("Enviar");
        btnSalir = new JButton("Salir");
        txtConversacion = new JTextArea(10, 30);
        txtConversacion.setEditable(false);
        scrollPane = new JScrollPane(txtConversacion);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.add(lblNombre);
        topPanel.add(txtNombre);
        topPanel.add(btnEmpezar);

        JPanel messagePanel = new JPanel();
        messagePanel.add(txtMensaje);
        messagePanel.add(btnEnviar);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnSalir);

        panel.add(topPanel);
        panel.add(scrollPane);
        panel.add(messagePanel);
        panel.add(bottomPanel);

        add(panel);

        btnEmpezar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtNombre.getText().equalsIgnoreCase("")) {
                }
            }
        });

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensaje = txtMensaje.getText();
                cliente.enviarMensaje(nombre + " --> " + mensaje + "\n");
                txtMensaje.setText("");
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliente.enviarMensaje("cerrar");
                setAlwaysOnTop(false);
                setVisible(false);
                dispose();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cliente Chat");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        txtConversacion.append((String) arg);
    }

   
}

