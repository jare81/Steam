/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import Codee.Administrador;
import Codee.JuegoManager;
import Codee.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Dell
 */
public class SteamPANEL extends JPanel {

    private String nombre = "";
    private String genero = "";
    private String desarrollador = "";
    private String lanzamiento = "";
    private String ruta = "";
    private String imagen = "";
    private int indice = 0;

    private ImageIcon noDown;
    private ImageIcon down;
    

    private Administrador user;
    private Usuario actual;
    private JuegoManager dev;

    private JPanel informacion;
    private GridBagConstraints gridT;

    private GridBagConstraints gridB;

    public SteamPANEL(Administrador user) {
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        this.user = user;
        actual = user.getUsuarioActual();
        dev = new JuegoManager();

        GridBagConstraints grid = new GridBagConstraints();
        grid.insets = new Insets(10, 10, 10, 10);
        Dimension size = new Dimension(200, 30);

        JPanel timeline = new JPanel(new GridBagLayout());
        timeline.setBackground(Color.LIGHT_GRAY);
        timeline.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timeline.setPreferredSize(new Dimension(1000, 200));
        gridT = new GridBagConstraints();
        gridT.insets = new Insets(10, 10, 10, 10);

        JPanel buscarP = new JPanel(new GridBagLayout());
        buscarP.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gridP = new GridBagConstraints();
        gridT.gridy = 1;
        gridT.gridx = 0;

        ImageIcon iconS = new ImageIcon("src/imags/xx.png");
        Image imgS = iconS.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedIconS = new ImageIcon(imgS);
        JLabel imageLabelS = new JLabel(resizedIconS);
        gridP.gridx = 0;
        gridP.gridy = 0;
        gridP.anchor = GridBagConstraints.WEST;
        gridP.insets = new Insets(0, 0, 0, 50);
        buscarP.add(imageLabelS, gridP);

        JTextField buscar = new JTextField("Descubre algo nuevo...");
        buscar.setFont(new Font("Arial", Font.BOLD, 20));
        buscar.setPreferredSize(new Dimension(600, 35));
        gridP.gridx = 1;
        gridP.gridy = 0;
        gridP.weightx = 1.0;
        gridP.fill = GridBagConstraints.HORIZONTAL;
        gridP.insets = new Insets(0, 0, 0, 5);
        buscarP.add(buscar, gridP);

        ImageIcon icon = new ImageIcon("src/imags/lupa32.png");
        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);
        JLabel imageLabel = new JLabel(resizedIcon);
        gridP.gridx = 2;
        gridP.gridy = 0;
        gridP.anchor = GridBagConstraints.CENTER;
        gridP.insets = new Insets(0, 0, 0, 0);
        buscarP.add(imageLabel, gridP);

        timeline.add(buscarP, gridT);

        JPanel bot = new JPanel(new GridBagLayout());
        gridB = new GridBagConstraints();
        gridB.insets = new Insets(10, 10, 10, 10);
        gridT.gridy = 2;
        gridT.gridx = 0;
        bot.setPreferredSize(new Dimension(800, 50));
        bot.setBackground(Color.LIGHT_GRAY);

        JButton fotoB = new JButton("Visualizar Biblioteca");
        fotoB.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 0;
        bot.add(fotoB, gridB);

        if (actual.getUsername().equalsIgnoreCase("admin")) {
            JButton agregar = new JButton("Agregar un juego");
            agregar.setPreferredSize(size);
            gridB.gridy = 0;
            gridB.gridx = 1;
            bot.add(agregar, gridB);

            agregar.addActionListener(ev -> {
                new AddJuegoFRAME().setVisible(true);
            });

            JButton eliminar = new JButton("Eliminar juego");
            eliminar.setPreferredSize(size);
            gridB.gridy = 0;
            gridB.gridx = 2;
            bot.add(eliminar, gridB);

            eliminar.addActionListener(ev -> {
            });
        }

        timeline.add(bot, gridT);

        JPanel panelD = new JPanel(new GridBagLayout());
        GridBagConstraints gridButtonConstraints = new GridBagConstraints();
        gridButtonConstraints.insets = new Insets(5, 5, 5, 5);
        gridButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridButtonConstraints.weightx = 1.0;
        final int d = 3;

        addButtonToPanelD(panelD, gridButtonConstraints, d);

        gridT.gridy = 3;
        gridT.gridx = 0;
        gridT.weightx = 1.0;
        gridT.weighty = 1.0;
        gridT.insets = new Insets(10, 20, 20, 20);
        gridT.fill = GridBagConstraints.BOTH;
        gridT.gridheight = 2;

        JScrollPane scrollPane2 = new JScrollPane(panelD);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setPreferredSize(new Dimension(300, 200));

        timeline.add(scrollPane2, gridT);

        informacion = new JPanel(new GridBagLayout());
        informacion.setBackground(Color.LIGHT_GRAY);
        informacion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        informacion.setPreferredSize(new Dimension(300, 200));
        GridBagConstraints gridF = new GridBagConstraints();
        gridF.insets = new Insets(10, 10, 10, 10);

        ImageIcon icon2 = new ImageIcon(imagen);
        Image img2 = icon2.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(img2);
        JLabel imageLabel2 = new JLabel(resizedIcon2);
        gridF.gridy = 0;
        gridF.gridx = 0;
        informacion.add(imageLabel2);

        add(informacion, BorderLayout.EAST);

        /*JPanel descripcion = new JPanel();
        descripcion.setBackground(Color.LIGHT_GRAY);
        descripcion.setLayout(new GridBagLayout());
        gridF.gridy = 1;
        gridF.gridx = 0;
        gridF.insets = new Insets(5, 5, 5, 5);
        informacion.add(descripcion, gridF);
        
        

        GridBagConstraints gridD = new GridBagConstraints();
        gridD.insets = new Insets(10, 10, 10, 10);

        // Primera fila
        gridD.gridy = 0;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Nombre:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel("  PVZ   "), gridD);

        // Segunda fila
        gridD.gridy = 1;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Genero:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel("  zombies   "), gridD);

        //tercera fila
        gridD.gridy = 2;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Desarrollador:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel("  Sepa la bola   "), gridD);

        //cuarta fila
        gridD.gridy = 3;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Lanzamiento:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel("  10/12/2024  "), gridD);
        //quinta fila

        gridD.gridy = 4;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Ruta:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel("  documentos/quetal   "), gridD);*/
 /*JPanel game = new JPanel(new GridBagLayout());
            GridBagConstraints gridS = new GridBagConstraints();
            game.setBackground(Color.LIGHT_GRAY);
            gridS.insets = new Insets(5, 5, 5, 5); 
            gridD.gridy = 5;
            gridD.gridx = 0;
            
            
            
            JButton back = new JButton("Añadir");
            gridB.gridy = 0;
            gridB.gridx = 1;
            game.add(back, gridS);
            
           
            JButton play = new JButton("Jugar");
            gridB.gridy = 0;
            gridB.gridx = 2;
            game.add(play, gridS);
            
            

            JButton next = new JButton("Reseña");
            gridB.gridy = 0;
            gridB.gridx = 3;
            game.add(next, gridS);
            
          
           
            
            
            informacion.add(game, gridD);*/
        add(timeline, BorderLayout.WEST);

    }

    private void addButtonToPanelD(JPanel panel, GridBagConstraints constraints, int d) {
        int componentCount = panel.getComponentCount();
        int row = componentCount / d;
        int col = componentCount % d;

        for (int i = 0; i < dev.getArray().size(); i++) {
            String juego = dev.getArray().get(i).getNombre();
            String foto = dev.getArray().get(i).getFoto();

            JButton newButton = new JButton();
            newButton.setPreferredSize(new Dimension(250, 350));
            newButton.setLayout(new BorderLayout());

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(new Color(0, 0, 0, 0));

            ImageIcon icon2 = new ImageIcon(foto);
            Image img2 = icon2.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon2 = new ImageIcon(img2);
            JLabel imageLabel2 = new JLabel(resizedIcon2);
            imageLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel juegoLabel = new JLabel(juego);
            juegoLabel.setHorizontalAlignment(SwingConstants.LEFT);
            juegoLabel.setFont(new Font("Arial Bold", Font.PLAIN, 17));
            juegoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            juegoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            buttonPanel.add(imageLabel2);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Espaciado entre imagen y texto
            buttonPanel.add(juegoLabel);

            newButton.add(buttonPanel, BorderLayout.CENTER);

            int index = i;
            newButton.addActionListener(ev -> {
                System.out.println("Índice: " + index + ", Juego: " + juego + " Foto " + foto);
                nombre = dev.getArray().get(index).getNombre();
                genero = dev.getArray().get(index).getGenero();
                desarrollador = dev.getArray().get(index).getDesarrollador();
                lanzamiento = dev.getArray().get(index).getLanzamiento();
                ruta = dev.getArray().get(index).getRuta();
                imagen = dev.getArray().get(index).getFoto();
                indice = index;
                mostrar();
            });

            constraints.gridx = col;
            constraints.gridy = row;
            panel.add(newButton, constraints);

            componentCount++;
            row = componentCount / d;
            col = componentCount % d;
        }

    }

    private boolean panelVisible = false;

    public void mostrar() {
        if (!panelVisible) {
            actualizarInformacion();
            gridT.gridy = 1;
            add(informacion, BorderLayout.EAST);
            revalidate();
            repaint();
            panelVisible = true;
        } else {
            actualizarInformacion();
            revalidate();
            repaint();
        }
    }

    private void actualizarInformacion() {
        // Limpiar el contenido del panel
        informacion.removeAll();

        informacion.setBackground(Color.LIGHT_GRAY);
        informacion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        informacion.setPreferredSize(new Dimension(300, 200));
        GridBagConstraints gridF = new GridBagConstraints();
        gridF.insets = new Insets(10, 10, 10, 10);

        // Agregar la imagen
        ImageIcon icon2 = new ImageIcon(imagen);
        Image img2 = icon2.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(img2);
        JLabel imageLabel2 = new JLabel(resizedIcon2);
        gridF.gridy = 0;
        gridF.gridx = 0;
        informacion.add(imageLabel2, gridF);

        // Crear el panel de descripción
        JPanel descripcion = new JPanel();
        descripcion.setBackground(Color.LIGHT_GRAY);
        descripcion.setLayout(new GridBagLayout());
        gridF.gridy = 1;
        gridF.gridx = 0;
        gridF.insets = new Insets(5, 5, 5, 5);
        informacion.add(descripcion, gridF);

        GridBagConstraints gridD = new GridBagConstraints();
        gridD.insets = new Insets(10, 10, 10, 10);

        // Primera fila
        gridD.gridy = 0;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Nombre:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(nombre), gridD);

        // Segunda fila
        gridD.gridy = 1;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Genero:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(genero), gridD);

        //tercera fila
        gridD.gridy = 2;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Desarrollador:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(desarrollador), gridD);

        //cuarta fila
        gridD.gridy = 3;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Lanzamiento:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(lanzamiento), gridD);
        //quinta fila

        gridD.gridy = 4;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Ruta:         "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(ruta), gridD);

        // Actualizar visualización del panel
        informacion.revalidate();
        informacion.repaint();

        JPanel game = new JPanel(new GridBagLayout());
        GridBagConstraints gridS = new GridBagConstraints();
        game.setBackground(Color.LIGHT_GRAY);
        gridS.insets = new Insets(5, 5, 5, 5);
        gridD.gridy = 5;
        gridD.gridx = 0;

       
        noDown = new ImageIcon(new ImageIcon("src/imags/nodown32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        down = new ImageIcon(new ImageIcon("src/imags/down32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        JButton bnAdd = new JButton(user.verificarJuego(nombre) ? down : noDown);
        gridB.gridy = 0;
        gridB.gridx = 1;
        game.add(bnAdd, gridS);

        bnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!user.verificarJuego(nombre)) {
                    bnAdd.setIcon(down);
                    user.agregarJuego(new File(ruta));
                } else { 
                    bnAdd.setIcon(noDown);
                    user.eliminarJuego(nombre);
                }

                bnAdd.setIcon(user.verificarJuego(nombre) ? down : noDown);
            }
        });

        informacion.add(game, gridD);

    }
    
    public void actualizarContenido() {
        // Aquí actualizas el contenido dinámico del panel
        System.out.println("Actualizando contenido de Steam...");
        // Por ejemplo, recargar listas, datos, etc.
    }

    public void actualizarContenido(String info) {
        // Método para actualizar contenido dinámico si es necesario
    }

}
