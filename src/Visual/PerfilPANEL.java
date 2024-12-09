/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class PerfilPANEL extends JPanel {

    private General user;
    private DefaultListModel<String> modelo;
    private DefaultListModel<String> modelo2;
    private  JList<String> solicitudes;
    private Usuario actual;

    public PerfilPANEL(General user) {
        this.user = user;
        actual = user.getUsuarioActual();
        setLayout(new BorderLayout());
        //per.setBackground(new Color(0x82898F));
        setBackground(new Color(0x536878));
        setFont(new java.awt.Font("Berlin Sans FB", 0, 17));
        //setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16));

        Usuario actual = user.getUsuarioActual();

        modelo = new DefaultListModel<>();
        modelo2 = new DefaultListModel<>();
        listaNotis();

        GridBagConstraints grid = new GridBagConstraints();
        grid.insets = new Insets(10, 10, 10, 10);
        Dimension size = new Dimension(200, 30);

        JPanel perfil = new JPanel(new GridBagLayout());
        perfil.setBackground(new Color(0x536878));
        perfil.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel amigos = new JPanel(new GridBagLayout());
        amigos.setBackground(new Color(0x536878));
        amigos.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //panel para imagen y para informacion
        JPanel per = new JPanel(new GridBagLayout());
        GridBagConstraints gridI = new GridBagConstraints();
        //gridI.insets = new Insets(10, 10, 10, 10);
        per.setPreferredSize(new Dimension(930, 200));
        //per.setBackground(new Color(0x82898F));
        per.setBackground(new Color(0x536878));
        grid.gridy = 0;
        grid.gridx = 0;

        ImageIcon imagenOriginal = new ImageIcon(actual.getFoto());
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);
        JLabel labelImagen = new JLabel(imagen);
        labelImagen.setPreferredSize(new Dimension(150, 150));
        gridI.gridy = 0;
        gridI.gridx = 0;
        gridI.gridheight = 3;
        per.add(labelImagen, gridI);

        gridI.gridheight = 1; 
        gridI.gridx = 1;

        
        gridI.gridy = 0;
        JTextArea info = new JTextArea();
        info.setPreferredSize(new Dimension(750, 150));
        info.setBackground(new Color(0x536878));
        info.setForeground(Color.WHITE);
        info.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        info.setFont(new Font("Arial Rounded MT Bold", 0, 16));
        info.setEditable(false);
        info.setText("\n  Username: " + actual.getUsername() + "\n"
                + "  Desde: " + actual.getFecha() + "\n"
                + "  Estado: " + actual.getActivo() + "\n"
                + "  Descripcion: " + actual.getDescripcion());
        per.add(info, gridI);

        perfil.add(per);

        //panel de btones en horizontal
        JPanel bot = new JPanel(new GridBagLayout());
        GridBagConstraints grid2 = new GridBagConstraints();
        grid2.insets = new Insets(10, 10, 10, 10);
        grid.gridy = 1;
        grid.gridx = 0;
        bot.setPreferredSize(new Dimension(800, 50));
        bot.setBackground(new Color(0x536878));

        JButton fotoB = new JButton("Cambiar foto");
        fotoB.setPreferredSize(size);
        estiloB(fotoB);
        grid2.gridy = 0;
        grid2.gridx = 0;
        bot.add(fotoB, grid2);

        JButton passB = new JButton("Cambiar contrasena");
        passB.setPreferredSize(size);
        estiloB(passB);
        grid2.gridy = 0;
        grid2.gridx = 1;
        bot.add(passB, grid2);

        JButton descr = new JButton("Cambiar descripcion");
        descr.setPreferredSize(size);
        estiloB(descr);
        grid2.gridy = 0;
        grid2.gridx = 2;
        bot.add(descr, grid2);

        JButton playsI = new JButton("Juegos ()");
        playsI.setPreferredSize(size);
        estiloB(playsI);
        grid2.gridy = 0;
        grid2.gridx = 3;
        bot.add(playsI, grid2);

        JButton songsI = new JButton("Canciones ()");
        songsI.setPreferredSize(size);
        estiloB(songsI);
        grid2.gridy = 0;
        grid2.gridx = 4;
        bot.add(songsI, grid2);

        perfil.add(bot, grid);

        fotoB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Selecciona una nueva foto de perfil");
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes (jpg, png, gif)", "jpg", "png", "gif"));

                int seleccion = fileChooser.showOpenDialog(null);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = fileChooser.getSelectedFile();
                    try {
                        String rutaDestino = "src/users/" + actual.getUsername() + "/foto_perfil.png";
                        Files.copy(archivoSeleccionado.toPath(), new File(rutaDestino).toPath(), StandardCopyOption.REPLACE_EXISTING);

                        actual.setFoto(rutaDestino);
                        ImageIcon nuevaImagen = new ImageIcon(archivoSeleccionado.getAbsolutePath());
                        Image imagenRedimensionada = nuevaImagen.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        labelImagen.setIcon(new ImageIcon(imagenRedimensionada));

                        JOptionPane.showMessageDialog(null, "Foto de perfil actualizada correctamente.");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error al actualizar la foto de perfil: " + ex.getMessage());
                    }
                }

            }
        });

        passB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPasswordField ante = new JPasswordField();
                JPasswordField confir = new JPasswordField();

                Object[] mensaje = {
                    "Anterior contraseña:", ante,
                    "Confirmar contraseña:", confir
                };

                int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Cambiar Contraseña", JOptionPane.OK_CANCEL_OPTION);

                if (opcion == JOptionPane.OK_OPTION) {
                    String anterior = new String(ante.getPassword());
                    String nueva = new String(confir.getPassword());

                    if (anterior.isEmpty() || nueva.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else if (!anterior.equals(actual.getPass())) {
                        JOptionPane.showMessageDialog(null, "La contraseña anterior es incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else if (nueva.equals(actual.getPass())) {
                        JOptionPane.showMessageDialog(null, "La contraseña nueva no puede ser igual que la anterior.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña cambiada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        actual.setPass(nueva);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Operación cancelada.");
                }
            }
        });

        descr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevaDescripcion = JOptionPane.showInputDialog(null, "Ingrese la nueva descripción:");
                if (nuevaDescripcion != null) {
                    actual.setDescripcion(nuevaDescripcion);
                    info.setText("\n  Username: " + actual.getUsername() + "\n"
                            + "  Desde: " + actual.getFecha() + "\n"
                            + "  Estado: " + actual.getActivo() + "\n"
                            + "  Descripcion: " + actual.getDescripcion());
                }
            }
        });

       
        JTextArea actividadA = new JTextArea();
        actividadA.setBackground(new Color(0xB5BAC9));
        per.setBackground(new Color(0x536878));
        grid.gridy = 2;
        grid.gridx = 0;
        grid.weightx = 1.0; 
        grid.weighty = 1.0; 
        grid.insets = new Insets(10, 20, 20, 20);
        grid.fill = GridBagConstraints.BOTH; 
        grid.gridheight = 2; 
        perfil.add(actividadA, grid);

        actividadA.setLineWrap(true);
        actividadA.setWrapStyleWord(true); 

        JScrollPane scrollPane2 = new JScrollPane(actividadA);
        scrollPane2.setPreferredSize(new Dimension(300, 200));

        perfil.add(scrollPane2, grid);

        
        
        
        
        //PANEL AMIGOS 
        JPanel buscarP = new JPanel();
        buscarP.setBackground(new Color(0x536878));
        GridBagConstraints gridP = new GridBagConstraints();
        grid.gridy = 1;
        grid.gridx = 0;
        
        ImageIcon icon = new ImageIcon("src/imags/lupa32.png");
        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Ajusta el tamaño de la imagen
        ImageIcon resizedIcon = new ImageIcon(img);
        JLabel imageLabel = new JLabel(resizedIcon);

        buscarP.add(imageLabel);

        JTextField buscarF = new JTextField("Busqueda (cantidad de amigos)");estiloF(buscarF);
        buscarF.setPreferredSize(new Dimension(300, 35)); 
        buscarF.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                buscarF.setText("");
            }
        });
        grid.gridy = 0;
        grid.gridx = 0;
        grid.weightx = 1.0; 
        grid.weighty = 0.0;
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.gridheight = 1;
        // new Insets(top, left, bottom, right);
        grid.insets = new Insets(20, 20, 0, 20); // Márgenes
        amigos.add(buscarF, grid);

        JPanel soli = new JPanel(new FlowLayout(FlowLayout.LEFT));
        soli.setBackground(new Color(0x536878));
        JButton buscarB = new JButton("Enviar Solicitud");estiloB(buscarB);
        soli.add(buscarB);
        grid.gridy = 1;
        grid.gridx = 0;
        grid.weightx = 1.0;
        grid.weighty = 0.0;
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.insets = new Insets(10, 20, 10, 20); 
        amigos.add(soli, grid);
        
        
        
        

        JList<String> buscarA = new JList<>(modelo);
        buscarA.setFont(new Font("Arial Rounded MT Bold", 0, 17));
        buscarA.setBackground(new Color(0xB5BAC9));


        cargarLista(user.getTodosUsuarios(), actual);

        JScrollPane scrollPane = new JScrollPane(buscarA);
        scrollPane.setPreferredSize(new Dimension(300, 400));

        grid.gridy = 2;
        grid.gridx = 0;
        grid.weightx = 1.0; 
        grid.weighty = 1.0; 
        grid.insets = new Insets(10, 20, 20, 20);
        grid.fill = GridBagConstraints.BOTH; 

        amigos.add(scrollPane, grid);
        
        
        JPanel botonesS = new JPanel(new GridBagLayout());
        //soli.setBackground(new Color(0x536878));
        GridBagConstraints grid3 = new GridBagConstraints();
        grid3.insets = new Insets(10, 10, 10, 10);
        
                        grid3.gridy = 0;
                        grid3.gridx = 0;
                        grid3.gridwidth = 2; 
                        grid3.anchor = GridBagConstraints.CENTER;
                        JLabel titleS = new JLabel("Solicitudes"); estiloF(titleS);
                        botonesS.add(titleS, grid3);
                        
                    JButton aceptar = new JButton("Aceptar");estiloB(aceptar);
                        grid3.gridy = 1;
                        grid3.gridx = 0;
                        grid3.gridwidth = 1;
                        grid3.anchor = GridBagConstraints.EAST;
                        botonesS.add(aceptar, grid3); 
                    JButton rechazar = new JButton("Rechazar");estiloB(rechazar);
                        grid3.gridx = 1;
                        grid3.anchor = GridBagConstraints.WEST; 
                        botonesS.add(rechazar, grid3); 
                        
        grid.gridy = 3; 
        grid.gridx = 0; 
        grid.weightx = 0.0; 
        grid.weighty = 0.0; 
        grid.fill = GridBagConstraints.NONE; 
        amigos.add(botonesS, grid);            
        
        solicitudes = new JList<>(modelo2);
        solicitudes.setFont(new Font("Arial Rounded MT Bold", 0, 17));
        solicitudes.setBackground(new Color(0xB5BAC9));
        cargarLista(user.getTodosUsuarios(), actual);
        JScrollPane scrollPane3 = new JScrollPane(solicitudes);
        scrollPane2.setPreferredSize(new Dimension(300, 400));

        grid.gridy = 4 ;
        grid.gridx = 0;
        grid.weightx = 1.0; 
        grid.weighty = 1.0; 
        grid.fill = GridBagConstraints.BOTH; 
        amigos.add(scrollPane3, grid);

        
        

        
        
        
        buscarF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String palabraClave = buscarF.getText().trim(); 
                ArrayList<Usuario> resultados;

                if (!palabraClave.isEmpty()) {
                    resultados = user.buscarUsuarios(palabraClave);

                } else {
                    resultados = user.getTodosUsuarios();
                }

                cargarLista(resultados, actual);
            }

        });

        buscarB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palabraClave = buscarF.getText().trim();
                ArrayList<Usuario> resultados = user.buscarUsuarios(palabraClave);

                int seleccionado = buscarA.getSelectedIndex();

                if (seleccionado != -1 && resultados != null && seleccionado < resultados.size()) {
                    Usuario usuarioSeleccionado = resultados.get(seleccionado);

                    actual.enviarSolicitud(usuarioSeleccionado);  
                } else {

                    JOptionPane.showMessageDialog(null, "No se econtro ningun usuario '" + buscarF.getText().trim() + "'");
                }

            }
        });

         aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> resultados = actual.getSolicitudesPendientes();

                int seleccionado = solicitudes.getSelectedIndex();

                if (seleccionado != -1 && resultados != null) {
                    String selec = resultados.get(seleccionado);
                    
                    actual.aceptarSolicitud(selec);
                    listaNotis();

                } else {

                    JOptionPane.showMessageDialog(null, "No se acepto la solicitud de " + resultados.get(seleccionado));
                }
            }
        });
         
         rechazar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> resultados = actual.getSolicitudesPendientes();

                int seleccionado = solicitudes.getSelectedIndex();

                if (seleccionado != -1 && resultados != null) {
                    String selec = resultados.get(seleccionado);
                    
                    actual.rechazarSolicitud(selec);
                    listaNotis();

                } else {

                    JOptionPane.showMessageDialog(null, "No se rechazo la solicitud de " + resultados.get(seleccionado));
                }
            }
        });
        
        
        add(perfil);
        add(amigos, BorderLayout.EAST);

    }
    
    public void listaNotis(){
         ArrayList<String> resultados = actual.getSolicitudesPendientes();
          modelo2.clear();

        for (String solis : resultados) {
            if (solis != null) {
                String estado;
                
                modelo2.addElement("  @ " + solis + " Te ha enviado una solicitud");
            }
        }
    }

    public void estiloB(JButton button) {
        button.setBackground(new Color(0x003D62));
        button.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        button.setForeground(new Color(255, 255, 255));
    }

    public void estiloF(JComponent com) {
        com.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        com.setForeground(new Color(0, 0, 0));
    }

   
    private void cargarLista(ArrayList<Usuario> usuarios, Usuario actual) {
        modelo.clear();

        for (Usuario usuario : usuarios) {
            if (!usuario.equals(actual) && !usuario.getUsername().equalsIgnoreCase("admin")) {
                String estado = actual.isAmigo(usuario) ? "  [ Amigo ]" : "  [ No amigo ]";
                modelo.addElement("  @ " + usuario.getUsername() + estado);
            }
        }
        if (modelo.isEmpty()) {
            modelo.addElement("No se encontraron usuarios.");
        }

    }

}

/*public class PerfilPANEL extends JPanel {

    private ManejoUsuario user;
    private DefaultListModel<String> modelo;
    private DefaultListModel<String> modelo2;
    private JLabel labelImagen;
    private JList<String> solicitudes;
    private Usuario actual;
    private JTextArea info;
    
    public PerfilPANEL(ManejoUsuario user) {
        this.user = user;
        this.actual = user.getUsuarioActual();

        setLayout(new BorderLayout());
        setBackground(new Color(0x536878));

        modelo = new DefaultListModel<>();
        modelo2 = new DefaultListModel<>();
        listaNotis();

        // Crear y agregar paneles
        JPanel perfilPanel = crearPanelPerfil();
        JPanel amigosPanel = crearPanelAmigos();

        add(perfilPanel, BorderLayout.CENTER);
        add(amigosPanel, BorderLayout.EAST);
    }

    private JPanel crearPanelPerfil() {
        JPanel perfil = new JPanel(new GridBagLayout());
        perfil.setBackground(new Color(0x536878));
        perfil.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints grid = new GridBagConstraints();
        grid.insets = new Insets(10, 10, 10, 10);

        // Panel de información del usuario
        JPanel per = crearPanelInfoUsuario();
        grid.gridy = 0;
        perfil.add(per, grid);

        // Panel de botones
        JPanel bot = crearPanelBotones();
        grid.gridy = 1;
        perfil.add(bot, grid);

        // Área de texto de actividad
        JTextArea actividadA = new JTextArea();
        actividadA.setBackground(new Color(0xB5BAC9));
        actividadA.setLineWrap(true);
        actividadA.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(actividadA);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        grid.gridy = 2;
        grid.weightx = 1.0;
        grid.weighty = 1.0;
        grid.fill = GridBagConstraints.BOTH;
        perfil.add(scrollPane, grid);

        return perfil;
    }

    private JPanel crearPanelInfoUsuario() {
        JPanel per = new JPanel(new GridBagLayout());
        per.setPreferredSize(new Dimension(930, 200));
        per.setBackground(new Color(0x536878));

        GridBagConstraints gridI = new GridBagConstraints();
        gridI.insets = new Insets(10, 10, 10, 10);

        // Imagen de perfil
        ImageIcon imagenOriginal = new ImageIcon(actual.getFoto());
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        labelImagen = new JLabel(new ImageIcon(imagenRedimensionada));
        labelImagen.setPreferredSize(new Dimension(150, 150));
        gridI.gridx = 0;
        gridI.gridheight = 3;
        per.add(labelImagen, gridI);

        // Información del usuario
        info = new JTextArea();
        info.setPreferredSize(new Dimension(750, 150));
        info.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        info.setEditable(false);
        info.setText("\n  Username: " + actual.getUsername() + "\n"
                + "  Desde: " + actual.getFecha() + "\n"
                + "  Estado: " + actual.getActivo() + "\n"
                + "  Descripcion: " + actual.getDescripcion());
        gridI.gridx = 1;
        gridI.gridheight = 1;
        per.add(info, gridI);

        return per;
    }

    private JPanel crearPanelBotones() {
        JPanel bot = new JPanel(new GridBagLayout());
        bot.setPreferredSize(new Dimension(800, 50));
        bot.setBackground(new Color(0x536878));

        GridBagConstraints grid2 = new GridBagConstraints();
        grid2.insets = new Insets(10, 10, 10, 10);

        Dimension size = new Dimension(200, 30);

        // Botones
        String[] botones = {"Cambiar foto", "Cambiar contraseña", "Cambiar descripción", "Juegos ()", "Canciones ()"};
        for (int i = 0; i < botones.length; i++) {
            JButton boton = new JButton(botones[i]);
            boton.setPreferredSize(size);
            estiloB(boton);
            grid2.gridx = i;
            bot.add(boton, grid2);

            // Agregar acción específica según el botón
            agregarAccionBoton(boton, i);
        }

        return bot;
    }

    private void agregarAccionBoton(JButton boton, int index) {
        switch (index) {
            case 0: // Cambiar foto
                boton.addActionListener(e -> cambiarFoto());
                break;
            case 1: // Cambiar contraseña
                boton.addActionListener(e -> cambiarContrasena());
                break;
            case 2: // Cambiar descripción
                boton.addActionListener(e -> cambiarDescripcion());
                break;
            // Agregar acciones adicionales para otros botones
        }
    }

    private JPanel crearPanelAmigos() {
        JPanel amigos = new JPanel(new GridBagLayout());
        amigos.setBackground(new Color(0x536878));
        amigos.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints grid = new GridBagConstraints();
        grid.insets = new Insets(10, 10, 10, 10);

        // Campo de búsqueda
        JTextField buscarF = new JTextField("Búsqueda (cantidad de amigos)");
        estiloF(buscarF);
        buscarF.setPreferredSize(new Dimension(300, 35));
        buscarF.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                buscarF.setText("");
            }
        });
        grid.gridy = 0;
        grid.fill = GridBagConstraints.HORIZONTAL;
        amigos.add(buscarF, grid);

        // Botón de búsqueda
        JButton buscarB = new JButton("Enviar Solicitud");
        estiloB(buscarB);
        grid.gridy = 1;
        amigos.add(buscarB, grid);

        // Lista de usuarios
        JList<String> buscarA = new JList<>(modelo);
        buscarA.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
        buscarA.setBackground(new Color(0xB5BAC9));

        JScrollPane scrollPane = new JScrollPane(buscarA);
        scrollPane.setPreferredSize(new Dimension(300, 400));
        grid.gridy = 2;
        grid.weightx = 1.0;
        grid.weighty = 1.0;
        grid.fill = GridBagConstraints.BOTH;
        amigos.add(scrollPane, grid);

        // Botones de solicitudes
        JPanel botonesS = crearBotonesSolicitudes();
        grid.gridy = 3;
        grid.weightx = 0.0;
        grid.weighty = 0.0;
        amigos.add(botonesS, grid);

        return amigos;
    }

    private JPanel crearBotonesSolicitudes() {
        JPanel botonesS = new JPanel(new GridBagLayout());
        GridBagConstraints grid3 = new GridBagConstraints();
        grid3.insets = new Insets(10, 10, 10, 10);

        JLabel titleS = new JLabel("Solicitudes");
        estiloF(titleS);
        grid3.gridx = 0;
        grid3.gridwidth = 2;
        botonesS.add(titleS, grid3);

        JButton aceptar = new JButton("Aceptar");
        estiloB(aceptar);
        grid3.gridy = 1;
        grid3.gridx = 0;
        botonesS.add(aceptar, grid3);

        JButton rechazar = new JButton("Rechazar");
        estiloB(rechazar);
        grid3.gridx = 1;
        botonesS.add(rechazar, grid3);

        return botonesS;
    }

    private void cambiarFoto() {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Selecciona una nueva foto de perfil");
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes (jpg, png, gif)", "jpg", "png", "gif"));

                int seleccion = fileChooser.showOpenDialog(null);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = fileChooser.getSelectedFile();
                    try {
                        String rutaDestino = "src/users/" + actual.getUsername() + "/foto_perfil.png";
                        Files.copy(archivoSeleccionado.toPath(), new File(rutaDestino).toPath(), StandardCopyOption.REPLACE_EXISTING);

                        actual.setFoto(rutaDestino);
                        ImageIcon nuevaImagen = new ImageIcon(archivoSeleccionado.getAbsolutePath());
                        Image imagenRedimensionada = nuevaImagen.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        labelImagen.setIcon(new ImageIcon(imagenRedimensionada));

                        JOptionPane.showMessageDialog(null, "Foto de perfil actualizada correctamente.");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error al actualizar la foto de perfil: " + ex.getMessage());
                    }
                }
    }

    private void cambiarContrasena() {
                JPasswordField ante = new JPasswordField();
                JPasswordField confir = new JPasswordField();

                Object[] mensaje = {
                    "Anterior contraseña:", ante,
                    "Confirmar contraseña:", confir
                };

                int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Cambiar Contraseña", JOptionPane.OK_CANCEL_OPTION);

                if (opcion == JOptionPane.OK_OPTION) {
                    String anterior = new String(ante.getPassword());
                    String nueva = new String(confir.getPassword());

                    if (anterior.isEmpty() || nueva.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else if (!anterior.equals(actual.getPass())) {
                        JOptionPane.showMessageDialog(null, "La contraseña anterior es incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else if (nueva.equals(actual.getPass())) {
                        JOptionPane.showMessageDialog(null, "La contraseña nueva no puede ser igual que la anterior.", "Error", JOptionPane.ERROR_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña cambiada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        actual.setPass(nueva);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Operación cancelada.");
                }
            
    }

    private void cambiarDescripcion() {
        String nuevaDescripcion = JOptionPane.showInputDialog(null, "Ingrese la nueva descripción:");
                if (nuevaDescripcion != null) {
                    actual.setDescripcion(nuevaDescripcion);
                    info.setText("\n  Username: " + actual.getUsername() + "\n"
                            + "  Desde: " + actual.getFecha() + "\n"
                            + "  Estado: " + actual.getActivo() + "\n"
                            + "  Descripcion: " + actual.getDescripcion());
                }
    }

    private void listaNotis() {
        ArrayList<String> resultados = actual.getSolicitudesPendientes();
        modelo2.clear();

        for (String solis : resultados) {
            if (solis != null) {
                modelo2.addElement("  @ " + solis + " te ha enviado una solicitud");
            }
        }
    }

    public void estiloB(JButton button) {
        button.setBackground(new Color(0x003D62));
        button.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        button.setForeground(new Color(255, 255, 255));
    }

    public void estiloF(JComponent com) {
        com.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        com.setForeground(new Color(0, 0, 0));
    }
}*/

