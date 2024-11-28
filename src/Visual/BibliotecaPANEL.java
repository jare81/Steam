/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Dell
 */
public class BibliotecaPANEL extends JPanel {
    public BibliotecaPANEL(){
         setLayout(new GridBagLayout());
        setBackground(Color.LIGHT_GRAY);
        
        GridBagConstraints grid1 = new GridBagConstraints();
        grid1.gridy = 0; 
        grid1.gridx = 0; 
        grid1.insets = new Insets(10, 10, 10, 10);
        
        JPanel musica = new JPanel(new GridBagLayout());
        musica.setBackground(Color.WHITE);
        musica.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        musica.setPreferredSize(new Dimension(1200, 350));
        
        add(musica, grid1); 
        
        
        
        
        
        GridBagConstraints grid2 = new GridBagConstraints();
        grid2.gridy = 1; 
        grid2.gridx = 0; 
        grid2.insets = new Insets(10, 10, 10, 10);
        
        JPanel juegos = new JPanel(new GridBagLayout());
        juegos.setBackground(Color.WHITE);
        juegos.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        juegos.setPreferredSize(new Dimension(1200, 350));

        add(juegos, grid2); 
    }
    
    public void actualizarContenido(String info) {
        // Método para actualizar contenido dinámico si es necesario
    }
}
