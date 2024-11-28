/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

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
import java.util.ArrayList;
import javax.swing.BorderFactory;
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
    
    private ImageIcon favIcon;
    private ImageIcon nofavIcon;
    
    public SpotifyPANEL() {
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        GridBagConstraints grid = new GridBagConstraints();
        grid.insets = new Insets(10, 10, 10, 10);
        Dimension size = new Dimension(200, 30);

        JPanel timeline = new JPanel(new GridBagLayout());
        timeline.setBackground(Color.LIGHT_GRAY);
        timeline.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timeline.setPreferredSize(new Dimension(1000, 200));
        GridBagConstraints gridT = new GridBagConstraints();
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
        GridBagConstraints gridB = new GridBagConstraints();
        gridB.insets = new Insets(10, 10, 10, 10);
        gridT.gridy = 2;
        gridT.gridx = 0;
        bot.setPreferredSize(new Dimension(800, 50));
        bot.setBackground(Color.LIGHT_GRAY);

        /*JButton fotoB = new JButton("▶ ⏹️ ⏸️");
        fotoB.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 0;
        bot.add(fotoB, gridB);

        JButton hhB = new JButton("♥ ♡ ❤");
        hhB.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 1;
        bot.add(hhB, gridB);*/

        JButton a = new JButton("Visualizar Biblioteca");
        a.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 0;
        bot.add(a, gridB);

        JButton b = new JButton(" Agregar una cancion ");
        b.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 1;
        bot.add(b, gridB);
        
        JButton s = new JButton(" Informacion ");
        s.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 2;
        bot.add(s, gridB);

        timeline.add(bot, gridT);

        //Cambiar text area por un componente mas dinamico
        JTextArea CAMBIAR = new JTextArea();
        gridT.gridy = 3;
        gridT.gridx = 0;
        gridT.weightx = 1.0; 
        gridT.weighty = 1.0; 
        gridT.insets = new Insets(10, 20, 20, 20);
        gridT.fill = GridBagConstraints.BOTH; 
        gridT.gridheight = 2;
        timeline.add(CAMBIAR, gridT);

        CAMBIAR.setLineWrap(true); // ancho limitado
        CAMBIAR.setWrapStyleWord(true); // palabras completas

        JScrollPane scrollPane2 = new JScrollPane(CAMBIAR);
        scrollPane2.setPreferredSize(new Dimension(300, 200));

        timeline.add(scrollPane2, gridT);

        JPanel informacion = new JPanel(new GridBagLayout());
        informacion.setBackground(Color.LIGHT_GRAY);
        informacion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        informacion.setPreferredSize(new Dimension(300, 200));
        GridBagConstraints gridF = new GridBagConstraints();
        gridF.insets = new Insets(10, 10, 10, 10);

        
            ImageIcon icon2 = new ImageIcon("src/imags/icn.jpg");
            Image img2 = icon2.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH); 
            ImageIcon resizedIcon2 = new ImageIcon(img2);
            JLabel imageLabel2 = new JLabel(resizedIcon2);
            gridF.gridy = 0;
            gridF.gridx = 0;
            informacion.add(imageLabel2);

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
                descripcion.add(new JLabel("Titulo:         "), gridD);

                gridD.gridx = 1;
                descripcion.add(new JLabel("  Acasis   "), gridD);

        // Segunda fila
                gridD.gridy = 1;
                gridD.gridx = 0;
                descripcion.add(new JLabel("Artista:         "), gridD);

                gridD.gridx = 1;
                descripcion.add(new JLabel("  Acasis   "), gridD);

        //tercera fila
                gridD.gridy = 2;
                gridD.gridx = 0;
                descripcion.add(new JLabel("Album:         "), gridD);

                gridD.gridx = 1;
                descripcion.add(new JLabel("  Acasis   "), gridD);

        //cuarta fila
                gridD.gridy = 3;
                gridD.gridx = 0;
                descripcion.add(new JLabel("Duracion:         "), gridD);

                gridD.gridx = 1;
                descripcion.add(new JLabel("  3:00   "), gridD);
        //quinta fila

                gridD.gridy = 4;
                gridD.gridx = 0;
                descripcion.add(new JLabel("Ruta:         "), gridD);

                gridD.gridx = 1;
                descripcion.add(new JLabel("  documentos/quetal   "), gridD);


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
                    
                    
                    if(play.getIcon() == playIcon){
                        play.setIcon(pauseIcon);
                    }else{
                        play.setIcon(playIcon);
                    }
                }
            });

        
            
            
            ImageIcon nextIcon = new ImageIcon("src/imags/next32.png");
            Image nextImage = nextIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            JButton next = new JButton(new ImageIcon(nextImage));
            gridB.gridy = 0;
            gridB.gridx = 3;
            song.add(next, gridS);
            
            nofavIcon = new ImageIcon(new ImageIcon("src/imags/heart32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            favIcon = new ImageIcon(new ImageIcon("src/imags/heartr32.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            
            
            JButton fav = new JButton(nofavIcon);
            gridB.gridy = 0;
            gridB.gridx = 4;
            song.add(fav, gridS);
            
            fav.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    
                    if(fav.getIcon() == nofavIcon){
                        fav.setIcon(favIcon);
                    }else{
                        fav.setIcon(nofavIcon);
                    }
                }
            });
            
            
            
            informacion.add(song, gridD);
            
        
        add(timeline, BorderLayout.WEST);
        add(informacion, BorderLayout.EAST);

    }
    
    private void estilosF(JComponent com){
        com.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        com.setForeground(new Color(0, 0, 0));
    }

    public void actualizarContenido(String info) {
        // Método para actualizar contenido dinámico si es necesario
    }
}
