/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import Codee.General;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Dell
 */
public class MenuFRAME extends JFrame {
     private General user;
    
    public MenuFRAME(General user) {
        this.user=user;
        setSize(800, 600);
        setBackground(new Color(0x536878));
        this.setLocationRelativeTo(null);

        setTitle("MENU");
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JPanel menu = new JPanel(new GridBagLayout());
        menu.setBackground(new Color(0x536878));
        GridBagConstraints grid = new GridBagConstraints();

        grid.gridx = 0;
        grid.gridy = GridBagConstraints.RELATIVE;
        grid.insets = new Insets(20, 0, 20, 0);

        ImageIcon imagenOriginal = new ImageIcon("src/imags/hh.png"); 
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH);
        
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);
        JLabel labelImagen = new JLabel(imagen);

        labelImagen.setPreferredSize(new Dimension(500, 250));  

        
        Dimension botonSize = new Dimension(250, 40);

        JButton registro = new JButton("Registro");
        JButton inicio = new JButton("Iniciar Sesion");
        //JButton AgregarAmigo =new JButton("Salir");
        //JButton Listar =new JButton("Listar");

        registro.setPreferredSize(botonSize);
        estiloB(registro);
        registro.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                RegistroFRAME frame = new RegistroFRAME(user);
                frame.setVisible(true);

            }

        });

        inicio.setPreferredSize(botonSize);
        estiloB(inicio);
        inicio.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                IniciarSesionFRAME frame = new IniciarSesionFRAME(user);
                frame.setVisible(true);
            }

        });

        grid.gridx = 0;
        grid.gridy = 0;
        menu.add(labelImagen, grid);

        // Colocar los botones debajo de la imagen
        grid.gridy = 1;
        menu.add(registro, grid);

        grid.gridy = 2;
        menu.add(inicio, grid);
        // menu.add(AgregarAmigo, grid);
        //menu.add(Listar, grid);

        add(menu);

    }
    public void estiloB(JButton button){
        button.setBackground(new Color(255,255,255));
        button.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        button.setForeground(new Color(0, 0, 0));
    }

    

}
