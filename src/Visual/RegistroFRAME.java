/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import Codee.Administrador;
import Codee.Usuario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Dell
 */
public class RegistroFRAME extends JFrame {
private Administrador user;
    
    public RegistroFRAME(Administrador user) {
        this.user=user;
        LocalDate today = LocalDate.now();
        
        setSize(800, 600);
        this.setLocationRelativeTo(null);

        setTitle("REGISTRO");
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JPanel registro = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.insets = new Insets(10, 10, 10, 10);

        Dimension size = new Dimension(200, 30);
        
        
        JLabel text = new JLabel("REGISTRO");
        text.setFont(new Font("Berlin Sans FB", Font.PLAIN, 36));
        text.setForeground(Color.WHITE); 
        text.setBackground(new Color(0x003D62));
        text.setOpaque(true); 
        text.setPreferredSize(new Dimension (300, 70));
        text.setHorizontalAlignment(SwingConstants.CENTER); // Alineación horizontal
        text.setVerticalAlignment(SwingConstants.CENTER);
        
        grid.gridy = 0;
        grid.gridx = 0;
        grid.gridwidth = 2; 
        registro.add(text, grid);
        
        grid.gridwidth = 1;

        JLabel userL = new JLabel("Username:");estiloF(userL);
        JTextField userT = new JTextField();estiloF(userT);
        userT.setPreferredSize(size);


        JLabel passL = new JLabel("Contraseña");estiloF(passL);
        JTextField passT = new JTextField();estiloF(passT);
        passT.setPreferredSize(size);

        JLabel fechaL = new JLabel("Fecha");estiloF(fechaL);
        JTextField fechaT = new JTextField();estiloF(fechaT);
        fechaT.setPreferredSize(size);
        fechaT.setEditable(false);


        JButton crearB = new JButton("Crear");
        estiloB(crearB);
        
        JButton volverB = new JButton("Volver");
        estiloB(volverB);

        // Añadir componentes al registro panel
        grid.gridx = 0;
        grid.gridy = 1;
        grid.anchor = GridBagConstraints.LINE_END;
        registro.add(userL, grid);

        grid.gridx = 1;
        grid.anchor = GridBagConstraints.LINE_START;
        registro.add(userT, grid);

        grid.gridx = 0;
        grid.gridy = 2;
        grid.anchor = GridBagConstraints.LINE_END;
        registro.add(passL, grid);

        grid.gridx = 1;
        grid.anchor = GridBagConstraints.LINE_START;
        registro.add(passT, grid);

        grid.gridx = 0;
        grid.gridy = 3;
        grid.anchor = GridBagConstraints.LINE_END;
        registro.add(fechaL, grid);

        grid.gridx = 1;
        grid.anchor = GridBagConstraints.LINE_START;
        registro.add(fechaT, grid);

        grid.gridx = 0;
        grid.gridy = 4;
        grid.anchor = GridBagConstraints.CENTER;  
        registro.add(crearB, grid);

        grid.gridx = 1; 
        grid.anchor = GridBagConstraints.CENTER; 
        registro.add(volverB, grid);

        add(registro);

        // Acciones de los botones
        userT.addFocusListener(new FocusListener(){
             @Override
            public void focusGained(FocusEvent e) {
                //cuando el enfoque esta en el 
            }
            
             @Override
            public void  focusLost(FocusEvent e){
                if(!userT.getText().isEmpty()){
                    fechaT.setText("" + today);
                }
            }

        });
        
        
        volverB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
                MenuFRAME frame = new MenuFRAME(user);
                frame.setVisible(true);
            }
        });

        crearB.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String userS= userT.getText().trim();
                String passS = passT.getText().trim();
                String fechaS = fechaT.getText().trim();
                
               if(!userS.isEmpty() && !passS.isEmpty() && !fechaS.isEmpty()){

                    if (!user.usuarioExiste(userS)) {

                        try {
                            if(user.agregarUser(userS, passS, fechaS /*, jj, kk, pp*/)){
                                JOptionPane.showMessageDialog(null, "Registrado Exitosamente");
                                setVisible(false);
                                new InterfazFRAME(user).setVisible(true);
                                
                            }else{
                                JOptionPane.showMessageDialog(null, "Algo salio mal, intenta de nuevo");
                            }
                        } catch (ClassNotFoundException ex) {
                            System.out.println("Error " + ex.getMessage());
                        }


                    } else{
                        JOptionPane.showMessageDialog(null, "Usuario existente");
                    }
                   
                   
               }else{
                   JOptionPane.showMessageDialog(null, "Favor Complete todos los campos");
               }
            }
        });
    }
    
    public void estiloB(JButton button){
        button.setPreferredSize( new Dimension(180, 40));
        button.setBackground(new Color(0x003D62));
        button.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        button.setForeground(new Color(255, 255, 255));
    }

     public void estiloF(JComponent component) {
        component.setFont((new Font("Arial Rounded MT Bold", 0, 16)));
       // component.setBackground(backgroundColor);
        component.setForeground(new Color(0, 0, 0));
        component.setPreferredSize(new Dimension(120, 30));

       
    }
   
}
