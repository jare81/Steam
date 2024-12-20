/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import Codee.JuegoManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddJuegoFRAME extends JFrame {
    private JTextField txtTitulo;
    private JTextField txtGenero;
    private JTextField txtDesarrollador;
    private JTextField txtRutaFoto;
    private JComboBox<String> cbDia;
    private JComboBox<String> cbMes;
    private JComboBox<String> cbAno;
    private JuegoManager manager;

    public AddJuegoFRAME() {
        manager = new JuegoManager();
        setTitle("Agregar Nuevo Juego");
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
        JLabel lblTitulo = createStyledLabel("Título del Juego:", labelFont);
        panelFormulario.add(lblTitulo, gbc);

        gbc.gridx = 1;
        txtTitulo = createStyledTextField(fieldFont);
        panelFormulario.add(txtTitulo, gbc);

        // Género
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblGenero = createStyledLabel("Género:", labelFont);
        panelFormulario.add(lblGenero, gbc);

        gbc.gridx = 1;
        txtGenero = createStyledTextField(fieldFont);
        panelFormulario.add(txtGenero, gbc);

        // Desarrollador
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblDesarrollador = createStyledLabel("Desarrollador:", labelFont);
        panelFormulario.add(lblDesarrollador, gbc);

        gbc.gridx = 1;
        txtDesarrollador = createStyledTextField(fieldFont);
        panelFormulario.add(txtDesarrollador, gbc);

        // Fecha de lanzamiento
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblFecha = createStyledLabel("Fecha de Lanzamiento:", labelFont);
        panelFormulario.add(lblFecha, gbc);

        // Panel para selección de fecha
        gbc.gridx = 1;
        JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        fechaPanel.setOpaque(false);
        
        // Crear combos para día, mes y año
        cbDia = new JComboBox<>();
        cbMes = new JComboBox<>();
        cbAno = new JComboBox<>();

        // Llenar combos
        llenarDias(cbDia);
        llenarMeses(cbMes);
        llenarAnos(cbAno);

        fechaPanel.add(cbDia);
        fechaPanel.add(cbMes);
        fechaPanel.add(cbAno);

        panelFormulario.add(fechaPanel, gbc);

        // Foto
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblFoto = createStyledLabel("Foto del Juego:", labelFont);
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

        JButton btnAgregar = createStyledButton("Agregar Juego");
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

    private void llenarDias(JComboBox<String> cbDia) {
        cbDia.addItem("Día");
        for (int i = 1; i <= 31; i++) {
            cbDia.addItem(String.format("%02d", i));
        }
    }

    private void llenarMeses(JComboBox<String> cbMes) {
        String[] meses = {"Mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        for (String mes : meses) {
            cbMes.addItem(mes);
        }
    }

    private void llenarAnos(JComboBox<String> cbAno) {
        cbAno.addItem("Año");
        int anoActual = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = anoActual; i >= 2000; i--) {
            cbAno.addItem(String.valueOf(i));
        }
    }

    private void seleccionarFoto() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter(
            "Archivos de imagen (jpg, jpeg, png, bmp, gif)", "jpg", "jpeg", "png", "bmp", "gif"
        );
        fileChooser.setFileFilter(filtroImagen);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            txtRutaFoto.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void validarYGuardar() {
        String titulo = txtTitulo.getText().trim();
        String genero = txtGenero.getText().trim();
        String desarrollador = txtDesarrollador.getText().trim();
        String rutaFoto = txtRutaFoto.getText().trim();

        String dia = (String) cbDia.getSelectedItem();
        String mes = (String) cbMes.getSelectedItem();
        String ano = (String) cbAno.getSelectedItem();

        if (titulo.isEmpty() || genero.isEmpty() || desarrollador.isEmpty() || 
            rutaFoto.isEmpty() || dia.equals("Día") || mes.equals("Mes") || ano.equals("Año")) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, complete todos los campos.", 
                "Campos Incompletos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // mes a numero
        String[] meses = {"", "01", "02", "03", "04", "05", "06", 
                          "07", "08", "09", "10", "11", "12"};
        String fechaString = ano + "-" + 
                             meses[cbMes.getSelectedIndex()] + "-" + 
                             String.format("%02d", Integer.parseInt(dia));

        // Mensaje de confirmación
         manager.agregarJuego(titulo, genero, desarrollador,fechaString, "", rutaFoto);
        
        JOptionPane.showMessageDialog(this, 
            "Juego agregado:\n" +
            "Título: " + titulo + "\n" +
            "Género: " + genero + "\n" +
            "Desarrollador: " + desarrollador + "\n" +
            "Fecha: " + fechaString + "\n" +
            "Ruta de foto: " + rutaFoto, 
            "Juego Agregado", 
            JOptionPane.INFORMATION_MESSAGE);
        
         

        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddJuegoFRAME::new);
    }
}