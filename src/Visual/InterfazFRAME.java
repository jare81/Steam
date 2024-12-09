package Visual;

import Codee.Administrador;
import Codee.Reproductor;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
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
import javax.swing.SwingConstants;


public class InterfazFRAME extends JFrame {
    private Administrador user;
    private CardLayout cardLayout;
    private JPanel contenido;
    private Reproductor rep;
    
    public InterfazFRAME(Administrador user) {
       this.user=user;
       rep = new Reproductor();
        
        setSize(500, 500);
        setTitle("INTERFAZ");

        this.setLocationRelativeTo(null);

        setResizable(false);
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.NORMAL);
        setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());

        
        setLayout(new BorderLayout());
        
        cardLayout = new CardLayout();
        contenido = new JPanel();
        contenido.setLayout(cardLayout);
        contenido.setBackground(Color.LIGHT_GRAY);
        add(contenido, BorderLayout.CENTER);
        
        SpotifyPANEL spotify = new SpotifyPANEL(user);
        
        SteamPANEL steam = new SteamPANEL(user);
        PerfilPANEL perfil = new PerfilPANEL(user);
        ChatPANEL chat = new ChatPANEL(user);
        BibliotecaPANEL biblio =  new BibliotecaPANEL(user);
        
        contenido.add(spotify, "Spotify");
        contenido.add(steam, "Steam");
        contenido.add(perfil, "Perfil");
        contenido.add(chat, "Chat");
        contenido.add(biblio, "Biblioteca");


        JPanel menu = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.insets = new Insets(10, 10, 10, 10);
        Dimension size = new Dimension(200, 30);
        
        ImageIcon imagenOriginal = new ImageIcon("src/imags/xx.png"); 
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);
        JLabel labelImagen = new JLabel(imagen);

        labelImagen.setPreferredSize(new Dimension(150, 150)); 
        
        /*JTextField nameT = new JTextField();
        nameT.setPreferredSize(size);
        nameT.setText(user.getUsuarioActual().getUsername());
        nameT.setEditable(false);
        nameT.setHorizontalAlignment(JTextField.CENTER);*/

        JButton perfilB = new JButton(user.getUsuarioActual().getUsername().toUpperCase());
        perfilB.setPreferredSize(size);
        perfilB.setContentAreaFilled(false); 
       
        
        JButton spotiB = new JButton("Spotify");
        spotiB.setPreferredSize(size);
        spotiB.setContentAreaFilled(false); 

        JButton steamB = new JButton("Steam");
        steamB.setPreferredSize(size);
        steamB.setContentAreaFilled(false); 
        
        JButton chatB = new JButton("Chat");
        chatB.setPreferredSize(size);
        chatB.setContentAreaFilled(false); 
        
        
        JButton biblioB = new JButton("Biblioteca");
        biblioB.setPreferredSize(size);
        biblioB.setContentAreaFilled(false); 
        
        JButton cerrarB = new JButton("Cerrar Sesion");
        cerrarB.setPreferredSize(size);
        cerrarB.setContentAreaFilled(false); 
        
        JLabel adminLabel = new JLabel("MODO ADMINISTRADOR");
        adminLabel.setForeground(Color.RED);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        adminLabel.setVisible(false);

        if (user.getUsuarioActual().getUsername().equalsIgnoreCase("admin")) {
            adminLabel.setVisible(true);
        }

       
        
        
        grid.gridx = 0;
        grid.gridy = 0;
        menu.add(labelImagen, grid);
        
        grid.gridy = 1;  // Fila 2
        menu.add(perfilB, grid);

        grid.gridy = 2;  // Fila 0
        menu.add(spotiB, grid);

        grid.gridy = 3;  // Fila 1
        menu.add(steamB, grid);
        
        grid.gridy = 4;  // Fila 1
        menu.add(chatB, grid);

        grid.gridy = 5;  // Fila 2
        menu.add(biblioB, grid);
        
        grid.gridy = 6;  // Fila 2
        menu.add(cerrarB, grid);
        
        
        grid.gridy = 7; 
        menu.add(adminLabel, grid);
        
        

        add(menu, BorderLayout.WEST);
        
        spotiB.addActionListener(e -> cardLayout.show(contenido, "Spotify"));
        
        steamB.addActionListener(e -> {
            // Actualiza el contenido del panel Steam
            cardLayout.show(contenido, "Steam");
        });
        
        chatB.addActionListener(e -> cardLayout.show(contenido, "Chat"));
        
        perfilB.addActionListener(e -> cardLayout.show(contenido, "Perfil"));
        
        biblioB.addActionListener(e -> {
            biblio.actualizarContenido(); // Actualiza el contenido del panel Biblioteca
            cardLayout.show(contenido, "Biblioteca");
        });
        
         cerrarB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                user.cerrarSesion();
              
                setVisible(false);
                new MenuFRAME(user).setVisible(true);
            }
        });


    }
    
    

    

}
