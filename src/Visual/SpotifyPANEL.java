/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import Codee.Administrador;
import Codee.Musica;
import Codee.Reproductor;
import Codee.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Dell
 */
public class SpotifyPANEL extends JPanel {

    private ImageIcon playIcon;
    private ImageIcon pauseIcon;
    private GridBagConstraints gridB;
    private JPanel timeline;

    private ImageIcon favIcon;
    private ImageIcon nofavIcon;
    private boolean isPlaying = false;
    private boolean isFav;

    private String titulo = "";
    private String artista = "";
    private String album = "";
    private String ruta = "";
    private String imagen = "";
    private int indice = 0;

    private Reproductor rep;
    private Administrador user;
    private JPanel informacion;
    private GridBagConstraints gridT;
    private Usuario actual;

    public SpotifyPANEL(Administrador user) {
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        rep = new Reproductor();
        this.user=user;
        

        GridBagConstraints grid = new GridBagConstraints();
        grid.insets = new Insets(10, 10, 10, 10);
        Dimension size = new Dimension(200, 30);

        timeline = new JPanel(new GridBagLayout());
        timeline.setBackground(Color.LIGHT_GRAY);
        timeline.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timeline.setPreferredSize(new Dimension(1000, 200));
        gridT = new GridBagConstraints();
        gridT.insets = new Insets(10, 10, 10, 10);

        JPanel buscarP = new JPanel();
        buscarP.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gridP = new GridBagConstraints();
        gridT.gridy = 1;
        gridT.gridx = 0;

        JTextField buscar = new JTextField("Descubre algo nuevo...");
        buscar.setFont(new Font("Arial", Font.BOLD, 20));
        buscar.setPreferredSize(new Dimension(600, 35));

        ImageIcon icon = new ImageIcon("src/imags/lupa32.png");
        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);
        JLabel imageLabel = new JLabel(resizedIcon);

        buscarP.add(imageLabel);
        buscarP.add(buscar);

        timeline.add(buscarP, gridT);

        JPanel bot = new JPanel(new GridBagLayout());
        gridB = new GridBagConstraints();
        gridB.insets = new Insets(10, 10, 10, 10);
        gridT.gridy = 2;
        gridT.gridx = 0;
        bot.setPreferredSize(new Dimension(800, 50));
        bot.setBackground(Color.LIGHT_GRAY);

        JButton biblioteca = new JButton("Visualizar Biblioteca");
        biblioteca.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 0;
        bot.add(biblioteca, gridB);

        JButton agregar = new JButton(" Agregar una cancion ");
        agregar.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 1;
        bot.add(agregar, gridB);

        JButton ver = new JButton(" Informacion ");
        ver.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 2;
        bot.add(ver, gridB);

        JButton s = new JButton(" Agregar boton");
        s.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 3;
        bot.add(s, gridB);

        timeline.add(bot, gridT);

        JPanel panelD = new JPanel(new GridBagLayout());
        GridBagConstraints gridButtonConstraints = new GridBagConstraints();
        gridButtonConstraints.insets = new Insets(5, 5, 5, 5); 
        gridButtonConstraints.fill = GridBagConstraints.HORIZONTAL; 
        gridButtonConstraints.weightx = 1.0; 
        final int d = 1; 

        addButtonToPanelD(panelD, gridButtonConstraints, d);

        gridT.gridy = 3;
        gridT.gridx = 0;
        gridT.weightx = 1.0;
        gridT.weighty = 1.0;
        gridT.insets = new Insets(10, 20, 20, 20);
        gridT.fill = GridBagConstraints.BOTH;
        gridT.gridheight = 2;
        timeline.add(panelD, gridT);

        JScrollPane scrollPane2 = new JScrollPane(panelD);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setPreferredSize(new Dimension(300, 200));

        timeline.add(scrollPane2, gridT);

        /* s.addActionListener((ActionEvent e) -> {
            /*addButtonToPanelD(panelD, gridButtonConstraints, "Botón " + (panelD.getComponentCount() + 1), d);
            panelD.revalidate();
            panelD.repaint();*/
        //});
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

        


        add(timeline, BorderLayout.WEST);
        //add(informacion, BorderLayout.EAST);

    }

    private void addButtonToPanelD(JPanel panel, GridBagConstraints constraints, int d) {
        int componentCount = panel.getComponentCount();
        int row = componentCount / d;
        int col = componentCount % d;

        for (int i = 0; i < rep.getArray().size(); i++) {
            String cancion = rep.getArray().get(i).getTitulo(); // Obtener la canción del ArrayList
            JButton newButton = new JButton(cancion);
            newButton.setPreferredSize(new Dimension(100, 60)); // Tamaño preferido

            // Agregar el índice y la canción al botón
            int index = i;
            newButton.addActionListener(ev -> {
                System.out.println("Índice: " + index + ", Canción: " + cancion);
                titulo = rep.getArray().get(index).getTitulo();
                album = rep.getArray().get(index).getAlbum();
                artista = rep.getArray().get(index).getArtista();
                ruta = rep.getArray().get(index).getRuta();
                imagen = rep.getArray().get(index).getPortada();
                indice = index;
                mostrar();
            });

            // Configurar las restricciones para el nuevo botón
            constraints.gridx = col;
            constraints.gridy = row;
            panel.add(newButton, constraints);

            // Actualizar posición
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
        Image img2 = icon2.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
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

        // Agregar información
        gridD.gridy = 0;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Titulo: "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(titulo), gridD);

        gridD.gridy = 1;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Artista: "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(artista), gridD);

        gridD.gridy = 2;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Album: "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(album), gridD);

        gridD.gridy = 3;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Duracion: "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel("3:00"), gridD); // Reemplaza con duración real si está disponible

        gridD.gridy = 4;
        gridD.gridx = 0;
        descripcion.add(new JLabel("Ruta: "), gridD);

        gridD.gridx = 1;
        descripcion.add(new JLabel(ruta), gridD);

        // Actualizar visualización del panel
        informacion.revalidate();
        informacion.repaint();

        JPanel song = new JPanel(new GridBagLayout());
        GridBagConstraints gridS = new GridBagConstraints();
        song.setBackground(Color.LIGHT_GRAY);
        gridS.insets = new Insets(5, 5, 5, 5);
        gridD.gridy = 4;
        gridD.gridx = 0;

        JLabel espacio = new JLabel("\n\n");
        gridB.gridy = 0;
        gridB.gridx = 0;
        song.add(espacio, gridS);

        ImageIcon backIcon = new ImageIcon("src/imags/previous32.png");
        Image backImage = backIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JButton back = new JButton(new ImageIcon(backImage));
        gridB.gridy = 0;
        gridB.gridx = 1;
        song.add(back, gridS);

        playIcon = new ImageIcon(new ImageIcon("src/imags/play32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        pauseIcon = new ImageIcon(new ImageIcon("src/imags/pause32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        JButton play = new JButton(playIcon);
        gridB.gridy = 0;
        gridB.gridx = 2;
        song.add(play, gridS);

        play.addActionListener(new ActionListener() {
            

            @Override
            public void actionPerformed(ActionEvent e) {

                if (isPlaying) {
                    play.setIcon(playIcon);
                    rep.detener(); 
                    isPlaying = false; 
                } else {
                    play.setIcon(pauseIcon); 
                    rep.reproducir(indice); 
                    isPlaying = true; 
                }
            }
        });

        ImageIcon nextIcon = new ImageIcon("src/imags/next32.png");
        Image nextImage = nextIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JButton next = new JButton(new ImageIcon(nextImage));
        gridB.gridy = 0;
        gridB.gridx = 3;
        song.add(next, gridS);

        
        isFav = user.verificarCancion(titulo);
        nofavIcon = new ImageIcon(new ImageIcon("src/imags/heart32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        favIcon = new ImageIcon(new ImageIcon("src/imags/heartr32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

         JButton fav = new JButton(isFav ? favIcon : nofavIcon);
        gridB.gridy = 0;
        gridB.gridx = 4;
        song.add(fav, gridS);

        fav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                
              
            if (!isFav) {
                fav.setIcon(favIcon); // Actualizar icono si se agrega a favoritos
                user.agregarCancion(new File(ruta)); // Agregar la canción a la biblioteca
                isFav = true;
            } else {
                fav.setIcon(nofavIcon); // Actualizar icono si se elimina de favoritos
                user.eliminarCancion(titulo); // Eliminar la canción de la biblioteca
                isFav = false;
            }
        

            }
        });

        informacion.add(song, gridD);
    }
    
    public boolean getPlaying(){
        return isPlaying;
    }

    private void estilosF(JComponent com) {
        com.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        com.setForeground(new Color(0, 0, 0));
    }

    public void actualizarContenido(String info) {
        // Método para actualizar contenido dinámico si es necesario
    }
}
