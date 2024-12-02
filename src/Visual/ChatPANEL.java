/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import Codee.Amigos;
import Codee.Administrador;
import Codee.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author Dell
 */
public class ChatPANEL extends JPanel {

    /*actividadA.setBackground(new Color(0xB5BAC9));
        per.setBackground(new Color(0x536878));*/
    private Administrador user;
    private Usuario actual;
    private Usuario usuario;
    private DefaultListModel<Amigos> modelo;
    private JTextArea areaT;
    private JList<Amigos> resultadosArea;
    
    private String foto;
    private String nombre;
    private Usuario temp;
    private JLabel labelImagen;
    private JTextArea info;

    public ChatPANEL(Administrador user) {
        this.user = user;
        actual = user.getUsuarioActual();
        setLayout(new BorderLayout());
        setBackground(new Color(0xB5BAC9));

        modelo = new DefaultListModel<>();
        foto = "src/imags/xx.png";
        nombre=" Chat general";
        
        // Panel izquierdo 
        JPanel panelArea = crearPanelArea();
        add(panelArea, BorderLayout.WEST);

        // Panel derecho
        JPanel amigosPanel = crearPanelAmigos();
        add(amigosPanel, BorderLayout.EAST);
        
        

    }

    private JPanel crearPanelArea() {
        JPanel panelArea = new JPanel(new GridBagLayout());

        panelArea.setPreferredSize(new Dimension(940, 100));
        panelArea.setBackground(new Color(0x536878));
        panelArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints grid = new GridBagConstraints();
        grid.insets = new Insets(10, 10, 10, 10);

        // Título
        grid.gridx = 0;
        grid.gridy = 0;
        grid.gridwidth = 2;
        JLabel titulo = new JLabel("COMPARTE CON TUS AMIGOS");
        estiloF(titulo);
        panelArea.add(titulo, grid);
        
        
        grid.gridy++;
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.weightx = 1.0;
        grid.weighty = 0.0;
        JPanel quien = new JPanel(new GridBagLayout());
        quien.setBackground(new Color(0x536878));
        
                
                    GridBagConstraints gridI = new GridBagConstraints();
                    gridI.insets = new Insets(10, 10, 10, 10); 
                    
                    

                    ImageIcon imagenOriginal = new ImageIcon(foto);
                    Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    ImageIcon imagen = new ImageIcon(imagenRedimensionada);
                    labelImagen = new JLabel(imagen);
                    gridI.gridy = 0;
                    gridI.gridx = 0;
                    quien.add(labelImagen, gridI);

                    gridI.gridx = 1;
                    gridI.gridy = 0;
                    gridI.gridwidth = 2;
                    gridI.fill = GridBagConstraints.HORIZONTAL;
                    gridI.weightx = 1.0;
                    gridI.weighty = 0.0;
                    
                    info = new JTextArea();
                    info.setPreferredSize(new Dimension(750, 50));
                    info.setBackground(new Color(0x536878));
                    info.setForeground(Color.WHITE);
                    info.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    info.setFont(new Font("Arial Rounded MT Bold", 0, 14));
                    info.setEditable(false);
                    
                    info.setText("\n  -> : " + nombre + "\n"
                            + "  Estado: " + actual.getFecha()  + "\n");
                    quien.add(info, gridI);
         panelArea.add(quien, grid);

        // Área de texto
        grid.gridy++;
        grid.fill = GridBagConstraints.BOTH;
        grid.weightx = 1.0;
        grid.weighty = 1.0;
        
        areaT = new JTextArea();estiloF(areaT);
        areaT.setLineWrap(true);
        areaT.setWrapStyleWord(true);
        areaT.setBackground(new Color(0xB5BAC9));
        areaT.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        JScrollPane scrollArea = new JScrollPane(areaT);
        scrollArea.setPreferredSize(new Dimension(400, 100)); 
        panelArea.add(scrollArea, grid);

        // Campo de texto y botón
        grid.gridy++;
        grid.gridwidth = 1;
        grid.weighty = 0.0;
        grid.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtBuscar = new JTextField("Di algo");
        txtBuscar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
        txtBuscar.setForeground(new Color(0, 0, 0));
        txtBuscar.setPreferredSize(new Dimension(200, 30));
        txtBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                txtBuscar.setText("");
            }
        });
        panelArea.add(txtBuscar, grid);

        grid.gridx++;
        JButton enviar = new JButton("Enviar");
        

        enviar.addActionListener(e -> {
            Amigos amigoSeleccionado = resultadosArea.getSelectedValue(); 
            if (amigoSeleccionado != null) {
                String mensaje = txtBuscar.getText();
                if (!mensaje.isBlank()) {
                    String mensajeCompleto = "" + mensaje;
                    
                    File directorioAmigo = amigoSeleccionado.getDirect(); 
                    File directorioUsuarioActual = actual.getDirect();
                    
                    amigoSeleccionado.guardarMensajeCompartido(mensajeCompleto, directorioUsuarioActual);
                    txtBuscar.setText("");

                    ArrayList<String> mensajes = amigoSeleccionado.cargarConversacionCompartida(amigoSeleccionado.getNombre(), actual.getDirect());
                    areaT.setText(""); 
                    for (String msg : mensajes) {
                        if (msg.startsWith(actual.getDirect().getName())) {
                            areaT.append("Tú: " + msg.substring(actual.getDirect().getName().length() + 2) + "\n\n");
                        } else {
                            areaT.append("Amigo: " + msg + "\n\n");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un amigo primero.");
            }
        });

       /* new Timer(1000, e -> {
        Amigos amigoSeleccionado = resultadosArea.getSelectedValue();
        if (amigoSeleccionado != null) {
            ArrayList<String> mensajes = amigoSeleccionado.cargarConversacionCompartida(amigoSeleccionado.getNombre(), actual.getDirect());
            areaT.setText(""); // Limpiar el área de texto
            for (String msg : mensajes) {
                areaT.append(msg + "\n");
            }
        }
    }).start();*/

        panelArea.add(enviar, grid);

        return panelArea;
    }

    private JPanel crearPanelAmigos() {
        JPanel amigosPanel = new JPanel(new GridBagLayout());
        amigosPanel.setBackground(new Color(0x536878));;
        amigosPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        GridBagConstraints grid = new GridBagConstraints();
        grid.insets = new Insets(10, 10, 10, 10);
        grid.fill = GridBagConstraints.HORIZONTAL;

        // Título
        grid.gridx = 0;
        grid.gridy = 0;
        JLabel titulo = new JLabel("AMIGOS DEL PERFIL");
        estiloF(titulo);
        amigosPanel.add(titulo, grid);

        // Campo de búsqueda
        grid.gridy++;
        JTextField txtBuscar = new JTextField("Búsqueda (cantidad de amigos)");
        txtBuscar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
        txtBuscar.setForeground(new Color(0, 0, 0));
        txtBuscar.setPreferredSize(new Dimension(200, 30));
        txtBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                txtBuscar.setText("");
            }
        });

        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String palabraClave = txtBuscar.getText().trim(); 
                ArrayList<Usuario> resultados;

                if (!palabraClave.isEmpty()) {
                    resultados = user.buscarUsuarios(palabraClave);

                } else {
                    resultados = user.getTodosUsuarios();
                }

                cargarLista(resultados, actual.getDirect());
            }

        });

        amigosPanel.add(txtBuscar, grid);

        // Botón y etiqueta
        grid.gridy++;
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonesPanel.setBackground(new Color(0x536878));
        JLabel buscarLabel = new JLabel("Amigos del perfil         ");
        estiloF(buscarLabel);
        JButton buscarBoton = new JButton("Iniciar Conversacion");
        botonesPanel.add(buscarLabel);
        botonesPanel.add(buscarBoton);
        amigosPanel.add(botonesPanel, grid);

        // Área de resultados
        grid.gridy++;
        //JList<String> resultadosArea = new JList<>(modelo); estiloF(resultadosArea);
        resultadosArea = new JList<>(modelo); 
        resultadosArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        resultadosArea.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.getNombre());
            label.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17)); 
            label.setOpaque(true);
            if (isSelected) {
                label.setBackground(new Color(0xB5BAC9));
                label.setForeground(Color.BLACK);
            } else {
                label.setBackground(new Color(0x536878));
                label.setForeground(Color.WHITE);
            }
            return label;
        });
        resultadosArea.setForeground(new Color(0, 0, 0));
        
        JScrollPane scrollPane = new JScrollPane(resultadosArea);
        scrollPane.setPreferredSize(new Dimension(200, 600));
        scrollPane.getViewport().setBackground(new Color(0x536878));
        scrollPane.setBackground(new Color(0x536878));
        amigosPanel.add(scrollPane, grid);

        buscarBoton.addActionListener(e -> {
            Amigos amigoSeleccionado = resultadosArea.getSelectedValue(); 
            if (amigoSeleccionado != null) {
                ArrayList<String> mensajes = amigoSeleccionado.cargarConversacionCompartida(amigoSeleccionado.getNombre(), actual.getDirect());
                temp = user.obtenerUsuario(amigoSeleccionado.getNombre());
                nombre = temp.getUsername().toUpperCase();
                foto = temp.getFoto();
                
                ImageIcon imagenOriginal = new ImageIcon(foto);
                Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                ImageIcon imagen = new ImageIcon(imagenRedimensionada);
                labelImagen.setIcon(imagen);  // Actualiza la imagen

                // Actualizar el JTextArea con el nombre y estado
                info.setText("\n  -> : " + nombre + "\n" + "  Estado: " + actual.getFecha() + "\n");

                
                areaT.setText(""); 
                for (String mensaje : mensajes) {
                    areaT.append(mensaje + "\n\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un amigo primero.");
            }
        });

        resultadosArea.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.getNombre());
            if (isSelected) {
                label.setBackground(new Color(0xB5BAC9));
                label.setOpaque(true);
            }
            return label;
        });

        return amigosPanel;
    }

    /*private void cargarLista(ArrayList<Usuario> usuarios, Usuario actual) {
        modelo.clear();

        for (Usuario usuario : usuarios) {
            if (usuario != null) {
                String estado;
                if (usuario.equals(actual)) {
                     estado = "  [ Tu ]";
                } else if (actual.isAmigo(usuario)) {
                    estado = "  [ Amigo ]";
                } else {
                    estado = "  [ No amigo ]";
                }
                modelo.addElement("  @ " + usuario.getUsername() + " " + estado);
            }
        }
          
        

    }*/
    private void cargarLista(ArrayList<Usuario> usuarios, File directorioUsuarioActual) {
        modelo.clear();
        for (Usuario usuario : usuarios) {
            Amigos amigo = new Amigos(usuario.getUsername(), directorioUsuarioActual);
            modelo.addElement(amigo);
        }
    }

    public void estiloF(JComponent com) {
        com.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
        com.setForeground(new Color(255, 255, 255));
    }

}
