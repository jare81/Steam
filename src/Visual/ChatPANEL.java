/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import Chat.Cliente;
import Chat.ClienteView;
import Chat.GestorConversaciones;
import Chat.Mensaje;
import Chat.Servidor;
import Chat.OtroChat;
import Codee.Amigos;
import Codee.General;
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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Dell
 */
public class ChatPANEL extends JPanel implements Observer {

    /*actividadA.setBackground(new Color(0xB5BAC9));
        per.setBackground(new Color(0x536878));*/
    
    //PARA CHAT
    private String nombre2;
   
    
    private OtroChat sv;
    private JTextField txtMensaje;
    private  JButton bnenviar;
      private JTextArea areaT;
     
    
     
     ArrayList<Usuario> resultados;
     
     boolean isOn=false;
     private Cliente cliente;
     private Cliente cliente2;
     private String mensaje;
    private GestorConversaciones gestor;
    private Usuario destino;
    
    
    
   
    
    
    private General user;
    private Usuario actual;
    private Usuario usuario;
    private DefaultListModel<String> modelo;
    private  JTextField txtBuscar;
  
    private JList<String> resultadosArea;
    
    private String foto;
    private String nombre;
    private Usuario temp;
    private JLabel labelImagen;
    private JTextArea info;

    public ChatPANEL(General user) {
        this.user = user;
        actual = user.getUsuarioActual();
        setLayout(new BorderLayout());
        setBackground(new Color(0xB5BAC9));

        modelo = new DefaultListModel<>();
        foto = "src/imags/discord.png";
        nombre=" Chat general";
        
        // Panel izquierdo 
        JPanel panelArea = crearPanelArea();
        add(panelArea, BorderLayout.WEST);

        // Panel derecho
        JPanel amigosPanel = crearPanelAmigos();
        add(amigosPanel, BorderLayout.EAST);
        
        File directorio = new File("Chat");
            if (!directorio.exists()) {
                directorio.mkdir();
            }
       
        
         
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
                    Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
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
                    info.setFont(new Font("Arial Rounded MT Bold", 0, 16));
                    info.setEditable(false);
                    
                    info.setText("" + nombre + "\n"
                            + "  Estado:  Activo");
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
        
        Servidor.getInstance(6000).addObserver(this);
        JScrollPane scrollArea = new JScrollPane(areaT);
        scrollArea.setPreferredSize(new Dimension(400, 100)); 
        panelArea.add(scrollArea, grid);

        // Campo de texto y botón
        grid.gridy++;
        grid.gridwidth = 1;
        grid.weighty = 0.0;
        grid.fill = GridBagConstraints.HORIZONTAL;

        txtMensaje = new JTextField("Di algo");
        txtMensaje.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
        txtMensaje.setForeground(new Color(0, 0, 0));
        txtMensaje.setPreferredSize(new Dimension(200, 30));
        txtMensaje.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                txtMensaje.setText("");
            }
        });
        panelArea.add(txtMensaje, grid);

        grid.gridx++;
        bnenviar = new JButton("Enviar");
        

        bnenviar.addActionListener(e -> enviarMensaje());

       

        panelArea.add(bnenviar, grid);

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
        txtBuscar = new JTextField("Búsqueda (cantidad de amigos)");
        txtBuscar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
        txtBuscar.setForeground(new Color(0, 0, 0));
        txtBuscar.setPreferredSize(new Dimension(200, 30));
        txtBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                txtBuscar.setText("");
            }
        });

        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarUsuarios();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarUsuarios();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarUsuarios();
            }
        });

        amigosPanel.add(txtBuscar, grid);

        // Botón y etiqueta
        grid.gridy++;
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonesPanel.setBackground(new Color(0x536878));
        JLabel buscarLabel = new JLabel("Amigos del perfil         ");
        estiloF(buscarLabel);
        JButton bnSeleccionar = new JButton("Iniciar Conversacion");
        botonesPanel.add(buscarLabel);
        botonesPanel.add(bnSeleccionar);
        amigosPanel.add(botonesPanel, grid);

        // Área de resultados
        grid.gridy++;
        //JList<String> resultadosArea = new JList<>(modelo); estiloF(resultadosArea);
        //resultados = user.getTodosUsuarios();
       // cargarLista(resultados, actual);
        resultadosArea = new JList<>(modelo);
          
        
        resultadosArea.setForeground(new Color(0, 0, 0));
        
        JScrollPane scrollPane = new JScrollPane(resultadosArea);
        scrollPane.setPreferredSize(new Dimension(200, 600));
        scrollPane.getViewport().setBackground(new Color(0x536878));
        scrollPane.setBackground(new Color(0x536878));
        amigosPanel.add(scrollPane, grid);
        

       
        bnSeleccionar.addActionListener(e -> {
            
        
         
           
                int seleccionado = resultadosArea.getSelectedIndex();

                 if (seleccionado != -1 && seleccionado < resultados.size())  {
                    Usuario usuarioSeleccionado = resultados.get(seleccionado);

                JOptionPane.showMessageDialog(null, "Seleccionado " + usuarioSeleccionado.getUsername());
                 /*Servidor.instancia.addObserver(this);
                 
                  
                  cliente = new Cliente(6000);*/
                 
                 destino =usuarioSeleccionado;
                 
                String nombreArchivo = GestorConversaciones.obtenerArchivoConversacion(actual.getUsername(), destino.getUsername());
                gestor = new GestorConversaciones(nombreArchivo);
                 
                 //String nombreArchivo = "Chat/" + actual.getUsername() + "_" + destino.getUsername() + ".bin";
                 
                 
                areaT.setText("");
                    List<Mensaje> mensajes = gestor.cargarConversacion();
                    for (Mensaje mensaje : mensajes) {
                        areaT.append(mensaje + "\n");
                    }
                 
                  iniciarServer();
                  nombre = actual.getUsername();
                
                ClienteView cv = new ClienteView(usuarioSeleccionado.getUsername());
                Servidor.instancia.addObserver(cv);
                cv.setVisible(true);
                
                OtroChat otroChat = OtroChat.getInstance(actual, destino);
                otroChat.setUsuarios(actual, destino);
               
                 
                   
                } else {

                    JOptionPane.showMessageDialog(null, "No se econtro ningun usuario '" + txtBuscar.getText().trim() + "'");
                }

            
        });
            

       

        return amigosPanel;
    }


    
    private void cargarLista(ArrayList<Usuario> usuarios, Usuario actual) {
        modelo.clear();

        for (Usuario usuario : usuarios) {
            if (!usuario.equals(actual)&& !usuario.getUsername().equalsIgnoreCase("admin")) {
                String estado = actual.isAmigo(usuario) ? "  [ Amigo ]" : "  [ No amigo ]";
                modelo.addElement("  @ " + usuario.getUsername() + estado);
            }
        }
        if (modelo.isEmpty()) {
            modelo.addElement("No se encontraron usuarios.");
        }

    }
    
    private void iniciarServer() {
          if (isOn) {
            JOptionPane.showMessageDialog(null, "El servidor ya está encendido.", "Servidor Activo", JOptionPane.INFORMATION_MESSAGE);

            OtroChat.getInstance(actual, destino).toFront();
            OtroChat.getInstance(actual, destino).repaint();
            return;
        }

        Servidor.getInstance(6000);
        Thread t = new Thread(Servidor.instancia);
        t.start();

        OtroChat sv = OtroChat.getInstance(actual, destino);
        //sv.setVisible(true);

        Servidor.instancia.addObserver(sv);
        Servidor.instancia.setVistaServidor(sv);

        isOn = true;
        JOptionPane.showMessageDialog(null, "Servidor iniciado correctamente.", "Servidor", JOptionPane.INFORMATION_MESSAGE);
}
    
    public void enviarMensaje(){
        
            cliente2 = new Cliente(6000);
            mensaje = txtMensaje.getText();
            
            Mensaje mensajeObj = new Mensaje(actual.getUsername(), destino.getUsername(), mensaje);
           // gestor.guardarMensaje(mensajeObj);

            
            cliente2.enviarMensaje(nombre + " --> " + mensaje + "\n");
            //areaT.append(mensajeObj + "\n");
            txtMensaje.setText("");
    }

    public void estiloF(JComponent com) {
        com.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
        com.setForeground(new Color(255, 255, 255));
    }

    @Override
   public void update(Observable o, Object arg) {
        areaT.append((String) arg);
    }
   
   public void notificarConexion(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            areaT.append(mensaje + "\n");
        });
    }
   
   private void buscarUsuarios() {
    String palabraClave = txtBuscar.getText().trim();
    if (palabraClave.isEmpty()) {
       resultados = user.getTodosUsuarios();
    } else {
        resultados = user.buscarUsuarios(palabraClave);
    }
    cargarLista(resultados, actual);
}
   
   
   
    
   
}
  
