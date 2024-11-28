/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Dell
 */
public class SteamPANEL extends JPanel {

    public SteamPANEL() {
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

        JButton fotoB = new JButton("Visualizar Biblioteca");
        fotoB.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 0;
        bot.add(fotoB, gridB);

        JButton hhB = new JButton("Agregar un juego");
        hhB.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 1;
        bot.add(hhB, gridB);

        JButton a = new JButton(" Informacion");
        a.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 2;
        bot.add(a, gridB);

        /*JButton b = new JButton("Agregar un juego");
        b.setPreferredSize(size);
        gridB.gridy = 0;
        gridB.gridx = 3;
        bot.add(b, gridB);*/

        timeline.add(bot, gridT);

        //Cambiar text area por un componente mas dinamico
        /* JTextArea CAMBIAR = new JTextArea();
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

        timeline.add(scrollPane2, gridT);*/
        JPanel CAMBIAR = new JPanel();
        CAMBIAR.setLayout(new GridBagLayout()); 
        GridBagConstraints buttoc = new GridBagConstraints();

        gridT.gridy = 3;
        gridT.gridx = 0;
        gridT.weightx = 1.0;
        gridT.weighty = 1.0;
        gridT.insets = new Insets(10, 20, 20, 20);
        gridT.fill = GridBagConstraints.BOTH;
        gridT.gridheight = 2;

        buttoc.fill = GridBagConstraints.BOTH; 
        buttoc.weightx = 1.0; 
        buttoc.insets = new Insets(10, 10, 10, 10);

        JScrollPane scrollPane2 = new JScrollPane(CAMBIAR);
        scrollPane2.setPreferredSize(new Dimension(300, 200));

        timeline.add(scrollPane2, gridT); 

        JButton button1 = new JButton("Botón 1");
        JButton button2 = new JButton("Botón 2");

        buttoc.fill = GridBagConstraints.NONE;
        buttoc.weightx = 1.0;
        buttoc.weighty = 1.0; 
        buttoc.insets = new Insets(10, 10, 10, 10); 

        buttoc.gridx = 0;
        buttoc.gridy = 0;
        buttoc.gridwidth = 1; 
        buttoc.gridheight = 1; 
        CAMBIAR.add(button1, buttoc);

        buttoc.gridx = 1;
        CAMBIAR.add(button2, buttoc);

        CAMBIAR.revalidate();
        CAMBIAR.repaint();

        JPanel informacion = new JPanel(new GridBagLayout());
        informacion.setBackground(Color.LIGHT_GRAY);
        informacion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        informacion.setPreferredSize(new Dimension(300, 200));
        GridBagConstraints gridF = new GridBagConstraints();
        gridF.insets = new Insets(10, 10, 10, 10);

        ImageIcon icon2 = new ImageIcon("src/imags/aa.png");
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
        descripcion.add(new JLabel("  documentos/quetal   "), gridD);
        
        
        
        
         JPanel game = new JPanel(new GridBagLayout());
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
            
          
           
            
            
            informacion.add(game, gridD);
        
        

        add(timeline, BorderLayout.WEST);
        add(informacion, BorderLayout.EAST);

    }

    public void actualizarContenido(String info) {
        // Método para actualizar contenido dinámico si es necesario
    }
}
