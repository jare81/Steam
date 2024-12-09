/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import Codee.Reproductor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.filechooser.FileNameExtensionFilter;

public class addMusicaFRAME extends JFrame {
    private JTextField txtTitulo;
    private JTextField txtArtista;
    private JTextField txtAlbum;
    private JTextField txtRutaMP3;
     private JTextField txtRutaFoto;
     private Reproductor rep;
   

    public addMusicaFRAME() {
        rep=new Reproductor();
        setTitle("Agregar Nueva Cancion");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridBagLayout());
        panelFormulario.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial Rounded MT Bold", Font.BOLD, 16);
        Font fieldFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblTitulo = createStyledLabel("Título de Canción", labelFont);
        panelFormulario.add(lblTitulo, gbc);

        gbc.gridx = 1;
        txtTitulo = createStyledTextField(fieldFont);
        panelFormulario.add(txtTitulo, gbc);

        // Género
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblArtista = createStyledLabel("Artista:", labelFont);
        panelFormulario.add(lblArtista, gbc);

        gbc.gridx = 1;
        txtArtista = createStyledTextField(fieldFont);
        panelFormulario.add(txtArtista, gbc);

        // Desarrollador
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblAlbum = createStyledLabel("Álbum:", labelFont);
        panelFormulario.add(lblAlbum, gbc);

        gbc.gridx = 1;
        txtAlbum = createStyledTextField(fieldFont);
        panelFormulario.add(txtAlbum, gbc);

        // Fecha de lanzamiento
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblMP3 = createStyledLabel("MP3:", labelFont);
        panelFormulario.add(lblMP3, gbc);
        
        gbc.gridx = 1;
        JPanel mp3Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mp3Panel.setOpaque(false);
        txtRutaMP3 = createStyledTextField(fieldFont);
        txtRutaMP3.setEditable(false);
        
        JButton btnSeleccionarMP3 = createStyledButton("Seleccionar MP3");
        btnSeleccionarMP3.addActionListener(e -> seleccionarMP3());

        mp3Panel.add(txtRutaMP3);
        mp3Panel.add(btnSeleccionarMP3);
        panelFormulario.add(mp3Panel, gbc);
        

        // Foto
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblFoto = createStyledLabel("Portada", labelFont);
        panelFormulario.add(lblFoto, gbc);

        gbc.gridx = 1;
        JPanel fotoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fotoPanel.setOpaque(false);
        txtRutaFoto = createStyledTextField(fieldFont);
        txtRutaFoto.setEditable(false);
        JButton btnSeleccionarFoto = createStyledButton("Seleccionar Foto");
        btnSeleccionarFoto.addActionListener(e -> seleccionarFoto());

        fotoPanel.add(txtRutaFoto);
        fotoPanel.add(btnSeleccionarFoto);
        panelFormulario.add(fotoPanel, gbc);

        // Botones
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setOpaque(false);

        JButton btnAgregar = createStyledButton("Agregar Canción");
        btnAgregar.addActionListener(e -> validarYGuardar());

        JButton btnCancelar = createStyledButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnCancelar);
        panelFormulario.add(panelBotones, gbc);

        // Agregar paneles
        mainPanel.add(panelFormulario, BorderLayout.CENTER);
        
        // Establecer contenido
        setContentPane(mainPanel);

        pack();
        setVisible(true);
    }

    private JLabel createStyledLabel(String texto, Font font) {
        JLabel label = new JLabel(texto);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        return label;
    }

    private JTextField createStyledTextField(Font font) {
        JTextField textField = new JTextField(20);
        textField.setFont(font);
        textField.setBackground(new Color(255, 255, 255, 200));
        textField.setBorder(
            
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
        return textField;
    }

    private JButton createStyledButton(String texto) {
        JButton button = new JButton(texto);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
       
        
        return button;
    }

    private void seleccionarFoto() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter(
            "Archivos de imagen (jpg, png)", "jpg", "png"
        );
        fileChooser.setFileFilter(filtroImagen);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            txtRutaFoto.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }
    
    private void seleccionarMP3() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtroMP3 = new FileNameExtensionFilter(
            "Archivos de audio MP3", "mp3"
        );
        fileChooser.setFileFilter(filtroMP3);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            txtRutaMP3.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void validarYGuardar() {
        String titulo = txtTitulo.getText().trim();
        String artista = txtArtista.getText().trim();
        String album = txtAlbum.getText().trim();
        String rutaCancion= txtRutaMP3.getText().trim();
        String rutaFoto = txtRutaFoto.getText().trim();

       

        if (titulo.isEmpty() || artista.isEmpty() || album.isEmpty() || 
            rutaFoto.isEmpty()  ) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, complete todos los campos.", 
                "Campos Incompletos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        
        rep.agregarCancion(titulo, artista, album, rutaCancion, rutaFoto);
        JOptionPane.showMessageDialog(this, 
            "Juego agregado:\n" +
            "Título: " + titulo + "\n" +
            "Género: " + artista + "\n" +
            "Desarrollador: " + album + "\n" +
            "Ruta de MP3: "  + rutaCancion +"\n" +
            "Ruta de foto: " + rutaFoto, 
            "Juego Agregado", 
            JOptionPane.INFORMATION_MESSAGE);

        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(addMusicaFRAME::new);
    }
}
