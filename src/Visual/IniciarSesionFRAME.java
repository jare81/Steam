package Visual;

import Codee.General;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Dell
 */
public class IniciarSesionFRAME extends JFrame {

    private General user;

    public IniciarSesionFRAME(General user) {
        this.user = user;

         setSize(800, 600);
        this.setLocationRelativeTo(null);

        setTitle("INICIAR SESION");
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JPanel registro = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.insets = new Insets(10, 10, 10, 10);

        Dimension size = new Dimension(200, 30);
        
        /*ImageIcon imagenOriginal = new ImageIcon("src/imags/ini.png");
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(350, 80, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);
        JLabel labelImagen = new JLabel(imagen);
        labelImagen.setPreferredSize(new Dimension(350, 80));*/
        
        JLabel text = new JLabel("INICIAR SESIÓN");
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
        JPasswordField passT = new JPasswordField();estiloF(passT);
        passT.setPreferredSize(size);

        JButton iniciarB = new JButton("Iniciar");
        estiloB(iniciarB);
        
        JButton volverB = new JButton("Volver");
        estiloB(volverB);
         JCheckBox mostrarCheck = new JCheckBox("Mostrar Contraseña"); 

      
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
        
        grid.gridx = 1;
        grid.gridy = 3;
        grid.anchor = GridBagConstraints.LINE_START;
        registro.add(mostrarCheck, grid);
       

        grid.gridx = 0;
        grid.gridy = 4;
        grid.gridwidth = 1;
        grid.anchor = GridBagConstraints.CENTER;
        registro.add(iniciarB, grid);

        grid.gridx = 1;
        registro.add(volverB, grid);

        add(registro);
        
        
        mostrarCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mostrarCheck.isSelected()) {
                    passT.setEchoChar((char) 0); 
                } else {
                    passT.setEchoChar('•');
                }
            }
        });

        // Acciones de los botones
        volverB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MenuFRAME frame = new MenuFRAME(user);
                frame.setVisible(true);
            }
        });

        iniciarB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userS = userT.getText();
                String passS = new String(passT.getPassword());

                if (!userS.isEmpty() && !passS.isEmpty()) {
                    
                    if (user.validarCredenciales(userS, passS)) {

                        setVisible(false);
                        new InterfazFRAME(user).setVisible(true);

                        
                    } else if (user.usuarioExiste(userS)) {
                        JOptionPane.showMessageDialog(null, "Verifique la informacion");

                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario no existe");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Favor Complete todos los campos");
                }
            }
        });
    }
    
    public void estiloB(JButton button){
        button.setPreferredSize( new Dimension(180, 40));
        button.setBackground(new Color(0x003D62));
        button.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
        button.setForeground(new Color(255, 255, 255));
    }
    
     public void estiloF(JComponent component) {
        component.setFont((new Font("Arial Rounded MT Bold", 0, 16)));
       // component.setBackground(backgroundColor);
        component.setForeground(new Color(0, 0, 0));
        component.setPreferredSize(new Dimension(120, 30));

       
    }

}
